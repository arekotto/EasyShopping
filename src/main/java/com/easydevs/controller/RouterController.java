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
    public String retrieveHeader(Model model,
                                 HttpServletResponse response,
                                 HttpServletRequest request,
                                 @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                                 @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {
        HeaderCommand headerCommand = new HeaderCommand();

        // check if user already logged in
        if (!userIdCookie.isEmpty() && !userTokenCookie.isEmpty()) {
            Integer userId = new Integer(userIdCookie);

            if(authenticationService.isTokenValid(userId, userTokenCookie)){
                StandardUser user = (StandardUser) userService.getUserById(userId);
                headerCommand.setUserName(user.getName());
                headerCommand.setUserLogin(user.getName());
                headerCommand.setIsLoggedIn(true);
            } else {
                response.addCookie(new Cookie("id", null));
                response.addCookie(new Cookie("token", null));
            }
        }

        model.addAttribute("headerCommand", headerCommand);

        return "main/header";
    }

    @RequestMapping("/footer")
    public String retrieveFooter(@CookieValue(value = "id", defaultValue = "") String userIdCookie,
                                 @CookieValue(value = "token", defaultValue = "") String userTokenCookie){
        return "main/footer";
    }

    @RequestMapping("/home")
    public String retrieveHomePage(Model model,
                                   HttpServletResponse response,
                                   HttpServletRequest request,
                                   @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                                   @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {
        return "home";
    }


}
