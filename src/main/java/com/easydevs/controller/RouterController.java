package com.easydevs.controller;

import com.easydevs.auth.AuthenticationService;
import com.easydevs.support.HeaderCommand;
import com.easydevs.user.UserService;
import com.easydevs.user.model.StandardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Arek on 31.10.2016.
 */

@Controller
public class RouterController {

//    @RequestMapping("/start")
//    @ResponseBody
//    public String test(){
//        return "yo mothafuckaaaaaa";
//    }


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    UserService userService;


    @RequestMapping("/header")
    public String retrieveHeader(){

        return "main/header";
    }

    @RequestMapping("/footer")
    public String retrieveFooter(){
        return "main/footer";
    }

    @RequestMapping("/home")
    public String retrieveHomePage() {
        return "home";
    }


}
