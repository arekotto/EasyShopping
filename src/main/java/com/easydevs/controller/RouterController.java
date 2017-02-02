package com.easydevs.controller;

import com.easydevs.support.HeaderCommand;
import com.easydevs.user.UserService;
import com.easydevs.user.model.StandardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Arek on 31.10.2016.
 */

@Controller
public class RouterController {

    @Autowired
    UserService userService;


//    @RequestMapping("/headerLoggedOut")
//    public String retrieveHeaderLoggedOut(){
//        return "main/header";
//    }
//
//    @RequestMapping("/header")
//    public String retrieveHeader(){
//        return "main/header";
//    }

    @RequestMapping("/header")
    public String retrieveHeaderLoggedIn(Model model,
                                         HttpServletRequest request,
                           @CookieValue(value = "id", defaultValue = "") String userIdCookie) {


        Boolean isRequestVerified = (Boolean) request.getAttribute("isRequestVerified");
        if (isRequestVerified != null && isRequestVerified) {
            Long userId = new Long(userIdCookie);
            StandardUser user = (StandardUser) userService.getUserById(userId);
            model.addAttribute("headerCommand", getHeaderCommand(user));
        }


        return "main/header";
    }

    @RequestMapping("/footer")
    public String retrieveFooter(){
        return "main/footer";
    }

    @RequestMapping("/home")
    public String retrieveHomePage() {
        return "redirect:/";
    }

    private HeaderCommand getHeaderCommand(StandardUser user) {
        HeaderCommand headerCommand = new HeaderCommand();
        headerCommand.setIsLoggedIn(true);
        headerCommand.setUserName(user.getName());
        headerCommand.setUserEmail(user.getEmail());
        headerCommand.setIsAdmin(user.getIsAdmin());
        return headerCommand;
    }
}
