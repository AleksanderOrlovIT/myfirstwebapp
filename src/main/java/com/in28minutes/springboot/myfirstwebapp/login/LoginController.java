package com.in28minutes.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AuthenticationService authenticationService;


    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
        public String gotoLoginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap modelMap){
        if(authenticationService.authenticate(name, password)) {
            modelMap.put("name", name);
            return "welcome";
        }
        modelMap.put("errorMessage", "Invalid Credentials! Please try again");
        return "login";
    }
}
