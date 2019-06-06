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
    ClientDao clientDao;

    @Autowired
    AccountDao accountDao;

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
        List<Client> existingClientByPassport = clientDao.getClientDataByPassport(newClient.getClient_passport());
        int clientIdForAccount;
        boolean accountCreationResult = false;

        if (existingClientByPassport.size() == 1){
            if ((!existingClientByPassport.get(0).getClient_name().equals(newClient.getClient_name())
                    || (!existingClientByPassport.get(0).getClient_lastname().equals(newClient.getClient_lastname()))))
                return ResponseEntity.badRequest().body("Couldn't register client with given data. Client with such passport number is already signed up.");

            clientIdForAccount = existingClientByPassport.get(0).getId();
            accountCreationResult = accountDao.createNewAccount(g.fromJson(clientData, Account.class), clientIdForAccount);
        } else {
            clientIdForAccount = clientDao.createNewClient(newClient);
            if (clientIdForAccount > -1)
                accountCreationResult = accountDao.createNewAccount(g.fromJson(clientData, Account.class), clientIdForAccount);
        }
        return accountCreationResult ? ResponseEntity.ok("Great! You've been signed up successfully!") :
                ResponseEntity.badRequest().body("Couldn't register your account data. Please, try again.");

    }

    @PostMapping("/transaction")
    @ResponseBody
    public ResponseEntity<?> logIn(@RequestBody String data) throws SQLException {


        return null;
    }
}
