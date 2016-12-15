package com.easydevs.controller;

import com.easydevs.user.StandardUser;
import com.easydevs.user.User;
import com.easydevs.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by arekotto on 09/12/2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @RequestMapping("/create/{userId}")
//    @ResponseBody
//    public String createUser(@PathVariable Integer userId) {
//        User user = new StandardUser(userId, "", "", "");
//        userService.createUser(user);
//        return "Created user with id: " + userId;
//    }
//
//    @RequestMapping("/get/{userId}")
//    @ResponseBody
//    public String getUser(@PathVariable Integer userId) {
//        User user = userService.getUser(userId);
//        return "Found user: " + user;
//    }
}
