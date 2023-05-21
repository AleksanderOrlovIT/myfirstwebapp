package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    @GetMapping("/")
        public String gotoWelcomePage(ModelMap modelMap) {
        modelMap.put("name", "in28minutes");
        return "welcome";
    }
}
