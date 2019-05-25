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

import java.sql.SQLException;
import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    ClientDao clientDao;

    @Autowired
    AccountDao accountDao;

    @GetMapping("/")
    public String homePage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
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

        if (existingClientByPassport.size() == 1
                && existingClientByPassport.get(0).getClient_passport().equals(newClient.getClient_passport()))
            return ResponseEntity.badRequest().body("Couldn't register client with given data. Client with such passport number is already signed up.");

        int newClientId = clientDao.createNewClient(newClient);

        if (newClientId > -1)
            return accountDao.createNewAccount(g.fromJson(clientData, Account.class), newClientId) ?
                    ResponseEntity.ok("Great! You've been signed up successfully!") : ResponseEntity.badRequest().body("Couldn't register your account data. Please, try again.");
        else
            return ResponseEntity.badRequest().body("Something went wrong while saving your data. Please, check your info and try again");
    }
}
