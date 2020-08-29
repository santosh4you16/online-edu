package com.onlineedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {

    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public String getHome() {
        return "register";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String getUserProfile() {
        return "user/userProfile";
    }
    
    @RequestMapping(value = "/createQuestion", method = RequestMethod.GET)
    public String getCreateQuestPage() {
        return "user/createQuestion";
    }
    
    @RequestMapping(value = "/createTestType", method = RequestMethod.GET)
    public String getCreateTestTypePage() {
        return "user/createTestType";
    }
    
   
}
