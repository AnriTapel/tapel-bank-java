package hello;

import com.google.gson.Gson;
import hello.dao.AccountsDao;
import hello.dao.AuthoritiesDao;
import hello.dao.UsersDao;
import hello.entities.Accounts;
import hello.entities.Authorities;
import hello.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(name = "account", required = false, defaultValue = "Log In") String account,
                            Model model) {
        model.addAttribute("account", account);
        model.addAttribute("title", "TapelBank - Login Page");
        return "login";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("title", "TapelBank - Access Denied");
        return "accessDenied";
    }

    @GetMapping("/")
    public String getHomePage(@RequestParam(name = "account", required = false, defaultValue = "Log In") String account,
                           Model model) {
        model.addAttribute("account", account);
        model.addAttribute("title", "TapelBank - Home Page");
        return "homePage";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model) {
        model.addAttribute("title", "TapelBank - Sign up");
        return "signUp";
    }

    @PostMapping("/sign-up")
    @ResponseBody
    public ResponseEntity<?> createNewClient(@RequestBody String clientData) throws SQLException {
        Gson g = new Gson();
        Users newClient = g.fromJson(clientData, Users.class);
        newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
        newClient.setEnabled(true);

        Users existingClientByPassport = usersDao.findByPassport(newClient.getPassport());
        Accounts newAccount = g.fromJson(clientData, Accounts.class);

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
            if (!hasSameName)
                return ResponseEntity.badRequest().body("Couldn't register client with given data. Client with such passport number is already signed up.");
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
        model.addAttribute("title", "TapelBank - Transactions");
        return "transaction";
    }
}
