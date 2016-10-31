package com.easydevs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Arek on 31.10.2016.
 */

@Controller
public class TestController {

    @RequestMapping("/start")
    @ResponseBody
    public String test(){
        return "yo mothafuckaaaaaa";
    }

    @RequestMapping("/stop")
    public String test2(){
        return "hello";
    }
}
