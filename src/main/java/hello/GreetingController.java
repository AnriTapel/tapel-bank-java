package hello;

import com.google.gson.Gson;
import hello.entities.Client;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

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
    public ResponseEntity<?> createNewClient(@RequestBody String client_data) {
        Gson g = new Gson();
        Client client = g.fromJson(client_data, Client.class);

        System.out.println("Balance: " + client.getClient_balance());

        if (client_data != null)
            return ResponseEntity.ok("success");
        else
            return ResponseEntity.badRequest().body("error");
    }
}
