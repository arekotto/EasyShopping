package com.easydevs.controller;

import com.easydevs.auth.AuthenticationService;
import com.easydevs.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by arekotto on 29/12/2016.
 */
@Controller
public class MainController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String showHomePage() {
        return "home";
    }
}