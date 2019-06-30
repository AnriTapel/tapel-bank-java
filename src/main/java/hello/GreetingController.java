package hello;

import com.google.gson.Gson;
import hello.dao.AccountsDao;
import hello.dao.AuthoritiesDao;
import hello.dao.OperationsDao;
import hello.dao.UsersDao;
import hello.entities.Accounts;
import hello.entities.Authorities;
import hello.entities.Operations;
import hello.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class GreetingController {

    @Autowired
    private UsersDao usersDao;
    @Autowired
    private AccountsDao accountsDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthoritiesDao authoritiesDao;
    @Autowired
    private OperationsDao operationsDao;

    private Gson gson = new Gson();

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("account", getCurrentUserDetail().getLastname());
        model.addAttribute("title", "TapelBank - Home Page");
        return "homePage";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("account", getCurrentUserDetail().getLastname());
        model.addAttribute("title", "TapelBank - Login Page");
        return "login";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("account", getCurrentUserDetail().getLastname());
        model.addAttribute("title", "TapelBank - Access Denied");
        return "accessDenied";
    }


    @GetMapping("/sign-up")
    public String getSignUpPage(Model model) {
        model.addAttribute("account", getCurrentUserDetail().getLastname());
        model.addAttribute("title", "TapelBank - Sign up");
        return "signUp";
    }

    @PostMapping("/sign-up")
    @ResponseBody
    public ResponseEntity<?> createNewClient(@RequestBody String clientData) throws SQLException {
        Users newClient = gson.fromJson(clientData, Users.class);
        newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
        newClient.setEnabled(true);

        Users existingClientByPassport = usersDao.findByPassport(newClient.getPassport());
        Accounts newAccount = gson.fromJson(clientData, Accounts.class);

        int clientIdForAccount = existingClientByPassport != null ? existingClientByPassport.getId() : -1;
        // If there already is client with same passport
        if (clientIdForAccount > -1) {
            List<Users> clientsByNameAndLastname = usersDao.findByNameAndLastname(newClient.getName(), newClient.getLastname());
            boolean hasSameName = false;
            for (Users existingClient : clientsByNameAndLastname) {
                if (existingClient.getId() == clientIdForAccount) {
                    hasSameName = true;
                    break;
                }
            }
            if (!hasSameName || usersDao.findByUsername(newClient.getUsername()) != null)
                return ResponseEntity.badRequest().body("Couldn't register client with given data. Client with such passport number or phone number is already signed up.");
            newAccount.setClientId(clientIdForAccount);
            accountsDao.save(newAccount);
        } else {
            usersDao.save(newClient);
            clientIdForAccount = usersDao.findByPassport(newClient.getPassport()).getId();
            newAccount.setClientId(clientIdForAccount);
            accountsDao.save(newAccount);
        }

        if (authoritiesDao.findByUsername(newClient.getUsername()) == null)
            authoritiesDao.save(new Authorities(newClient.getUsername(), newAccount.getCvc().equals("001") ? "ROLE_ADMIN" : "ROLE_USER", clientIdForAccount));

        return accountsDao.findByAccount(newAccount.getAccount()) != null ? ResponseEntity.ok("Great! You've been signed up successfully!") :
                ResponseEntity.badRequest().body("Couldn't register your account data. Please, try again.");

    }


    @GetMapping("/transaction")
    public String getTransactionPage(Model model) {
        model.addAttribute("account", getCurrentUserDetail().getLastname());
        model.addAttribute("title", "TapelBank - Transactions");
        model.addAttribute("operation", "transaction");
        return "operation";
    }

    @GetMapping("/payment")
    public String getPaymentPage(Model model) {
        model.addAttribute("account", getCurrentUserDetail().getLastname());
        model.addAttribute("title", "TapelBank - Payments");
        model.addAttribute("operation", "payment");
        return "operation";
    }

    @GetMapping("/transaction/checkReceiver/{account}")
    public ResponseEntity<?> makeTransaction(@PathVariable String account, Model model) {
        Accounts receiver = accountsDao.findByAccount(account.replaceAll("%20", " "));
        if (receiver != null)
            return ResponseEntity.ok(usersDao.findById(receiver.getClientId()).getName() + " " + usersDao.findById(receiver.getClientId()).getLastname());
        else
            return ResponseEntity.badRequest().body("Not found");

    }

    @PostMapping("/operation")
    public ResponseEntity<?> makeOperation(@RequestBody Map<String, Object> body) throws SQLException {
        // Accounts receiverAccount = accountsDao.findByAccount(body.get("receiver_account").toString());
        Accounts senderAccount = null;

        for (Accounts sender : accountsDao.findAllByClientId(getCurrentUserDetail().getId())) {
            if (sender.getBalance() >= Double.parseDouble(body.get("operation_sum").toString())) {
                senderAccount = sender;
                break;
            }
        }
        if (senderAccount == null)
            return ResponseEntity.badRequest().body("Operation failed. None of your accounts has enough funds for this transaction sum.");

        String receiverAccount = body.get("receiver_account").toString();
        Double operationSum = Double.parseDouble(body.get("operation_sum").toString());
        int receiverId = -1;
        // Need to find table row by identifier, so jpa could do all by itself:
        accountsDao.findById(senderAccount.getId()).setBalance(
                senderAccount.getBalance() - operationSum);

        if (Objects.equals(body.get("type").toString(), "0")){
            Accounts receiverAccountData = accountsDao.findByAccount(receiverAccount);
            accountsDao.findById(receiverAccountData.getId()).setBalance(
                    receiverAccountData.getBalance() + operationSum);
            receiverId = receiverAccountData.getClientId();
        }

        operationsDao.save(new Operations(
                senderAccount.getClientId(),
                senderAccount.getAccount(),
                receiverId,
                receiverAccount,
                operationSum,
                new Date(System.currentTimeMillis()),
                body.get("comment").toString()
        ));

        return ResponseEntity.ok(
                "Operations from your account " + senderAccount.getAccount() + " to "
                        + receiverAccount + " was successfully held. " +
                        "\nYour current balance is " +
                        accountsDao.findByAccount(senderAccount.getAccount()).getBalance()
        );
    }

    @GetMapping("/history")
    public String getHistoryDetails(Model model){
        List<Operations> transactionsOfUser = new ArrayList<Operations>();
        List<Operations> paymentsOfUser = new ArrayList<Operations>();

        // Adding to list transactions sent by user
        transactionsOfUser.addAll(operationsDao.findAllBySenderId(getCurrentUserDetail().getId())
                .stream().filter(it -> (it.getReceiverId() > 0))
                .collect(Collectors.toList()));
        // Adding to list transactions received by user
        transactionsOfUser.addAll(operationsDao.findAllByReceiverId(getCurrentUserDetail().getId()));

        // Adding to list payments made by user
        paymentsOfUser.addAll(operationsDao.findAllBySenderId(getCurrentUserDetail().getId())
                .stream().filter(it -> (it.getReceiverId() == -1))
                .collect(Collectors.toList()));

        Collections.sort(transactionsOfUser);
        Collections.sort(paymentsOfUser);

        model.addAttribute("transactions", transactionsOfUser);
        model.addAttribute("payments", paymentsOfUser);
        model.addAttribute("account", getCurrentUserDetail().getLastname());
        model.addAttribute("title", "TapelBank - История операций");
        return "history";
    }

    private Users getCurrentUserDetail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails)
            return usersDao.findByUsername(((UserDetails) principal).getUsername());
        else {
            Users currentUser = new Users();
            currentUser.setLastname("Log-in");
            return currentUser;
        }

    }
}
