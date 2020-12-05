package pl.coderslab.gov_app;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping("/login")
    public String loginForm(){
        return "Login";
    }

    @GetMapping("/noaccess")
    public String noAccess(){
        return "Noaccess";
    }

}