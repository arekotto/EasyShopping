package com.easydevs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/header")
    public String retrieveHeader() {
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
