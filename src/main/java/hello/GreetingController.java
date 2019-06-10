package hello;

import com.google.gson.Gson;
import hello.dao.AccountDao;
import hello.dao.ClientDao;
import hello.entities.Account;
import hello.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "account", required = false, defaultValue = "Log In") String account,
                            Model model) {
        model.addAttribute("account", account);
        model.addAttribute("title", "TapelBank - Login Page");
        return "login";
    }

    @GetMapping("/")
    public String homePage(@RequestParam(name = "account", required = false, defaultValue = "Log In") String account,
                           Model model) {
        model.addAttribute("account", account);
        model.addAttribute("title", "TapelBank - Home Page");
        return "homePage";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("title", "TapelBank - Sign up");
        return "signUp";
    }

    @PostMapping("/sign-up")
    @ResponseBody
    public ResponseEntity<?> createNewClient(@RequestBody String clientData) throws SQLException {
        Gson g = new Gson();
        Client newClient = g.fromJson(clientData, Client.class);
        Client existingClientByPassport = clientDao.findByClientPassport(newClient.getClientPassport());
        Account newAccount = g.fromJson(clientData, Account.class);
        newAccount.setClientKeyword(passwordEncoder.encode(newAccount.getClientKeyword()));
        int clientIdForAccount = existingClientByPassport != null ? existingClientByPassport.getId() : -1;
        // If there already is client with same passport
        if (clientIdForAccount > -1) {
            List<Client> clientsByNameAndLastname = clientDao.findByClientNameAndClientLastname(newClient.getClientName(), newClient.getClientLastname());
            boolean hasSameName = false;
            for (Client existingClient : clientsByNameAndLastname) {
                if (existingClient.getId() == clientIdForAccount) {
                    hasSameName = true;
                    break;
                }
            }
            if (!hasSameName)
                return ResponseEntity.badRequest().body("Couldn't register client with given data. Client with such passport number is already signed up.");
            newAccount.setClientId(clientIdForAccount);
            accountDao.save(newAccount);
        } else {
            clientDao.save(newClient);
            clientIdForAccount = clientDao.findByClientPassport(newClient.getClientPassport()).getId();
            newAccount.setClientId(clientIdForAccount);
            accountDao.save(newAccount);
        }
        return accountDao.findByClientAccount(newAccount.getClientAccount()) != null ? ResponseEntity.ok("Great! You've been signed up successfully!") :
                ResponseEntity.badRequest().body("Couldn't register your account data. Please, try again.");

    }

    @PostMapping("/transaction")
    @ResponseBody
    public ResponseEntity<?> logIn(@RequestBody String data) throws SQLException {


        return null;
    }
}
