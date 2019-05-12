package hello;

import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("title", "TapelBanl - Sign up");
        return "signUp";
    }
}
