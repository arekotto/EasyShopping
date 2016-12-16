package com.easydevs.controller;

import com.easydevs.user.StandardUser;
import com.easydevs.user.User;
import com.easydevs.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

/**
 * Created by arekotto on 15/12/2016.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    // just random stuff for testing

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable Integer userId) {
        User user = userService.getUser(userId);
        return user;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public void updateUser(@PathVariable Integer userId, @RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(path = "/test-user", method = RequestMethod.GET)
    @ResponseBody
    public void tesUserCreate() {
        User testUser = new StandardUser(0, "Test", "Testowy", "123");
        this.createUser(testUser);
        User user = this.getUser(new Integer(0));
        log.info(user.getLogin(), user.getId(), user.getName());
        userService.changeUserLogin(0, "Testowy2");
    }
}
