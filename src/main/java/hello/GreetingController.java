package hello;

import com.google.gson.Gson;
import hello.dao.AccountDao;
import hello.dao.ClientDao;
import hello.entities.Account;
import hello.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
import java.sql.SQLException;
import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/login")
    public String loginPage(@RequestParam(name="account", required=false, defaultValue="Log In") String account,
                            Model model) {
        model.addAttribute("account", account);
        model.addAttribute("title", "TapelBank - Login Page");
        return "login";
    }

    @GetMapping("/")
    public String homePage(@RequestParam(name="account", required=false, defaultValue="Log In") String account,
                           Model model) {
        model.addAttribute("account", account);
        model.addAttribute("title", "TapelBank - Home Page");
        return "homePage";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model){
        model.addAttribute("title", "TapelBank - Sign up");
        return "signUp";
    }

    @PostMapping("/sign-up/new-client")
    @ResponseBody
    public ResponseEntity<?> createNewClient(@RequestBody String clientData) throws SQLException {
        Gson g = new Gson();
        Client newClient = g.fromJson(clientData, Client.class);
        Client existingClientByPassport = clientDao.findByClientPassport(newClient.getClientPassport());
        Account newAccount = g.fromJson(clientData, Account.class);
        int clientIdForAccount = existingClientByPassport.getId();

        if (clientIdForAccount > -1){
            if (clientIdForAccount != clientDao.findByClientNameAndClientLastname(newClient.getClientName(), newClient.getClientLastname()).getId())
                return ResponseEntity.badRequest().body("Couldn't register client with given data. Client with such passport number is already signed up.");
            newAccount.setClientId(clientIdForAccount);
            accountDao.save(newAccount);
        } else {
            clientDao.save(newClient);
            clientIdForAccount = clientDao.findByClientPassport(newClient.getClientPassport()).getId();
            if (clientIdForAccount > -1) {
                newAccount.setClientId(clientIdForAccount);
                accountDao.save(newAccount);
            }
        }
        return accountDao.findByClientAccount(newAccount.getClientAccount()).getId() > -1 ? ResponseEntity.ok("Great! You've been signed up successfully!") :
                ResponseEntity.badRequest().body("Couldn't register your account data. Please, try again.");

    }

    @PostMapping("/transaction")
    @ResponseBody
    public ResponseEntity<?> logIn(@RequestBody String data) throws SQLException {


        return null;
    }
}
