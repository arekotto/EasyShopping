package com.easydevs.controller;

import com.easydevs.user.UserService;
import com.easydevs.user.UserType;
import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by arekotto on 15/12/2016.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    // just random stuff for testing

    @Autowired
    private UserService userService;

    /**
     * The Log.
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Create user string.
     *
     * @param login the login
     * @return the string
     */
    @RequestMapping(path = "/user/create")
    @ResponseBody
    public String createUser(@RequestParam String login) {
        StandardUser user = (StandardUser) userService.createNewUser(UserType.STANDARD);
        user.setEmail(login);
        userService.updateUser(user);

        return "Created user: " + user;
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    @RequestMapping(path = "user/{userId}")
    @ResponseBody
    public String getUser(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return "Found user: " + user;
    }

    /**
     * Update user string.
     *
     * @param userId   the user id
     * @param newLogin the new login
     * @return the string
     */
    @RequestMapping(path = "user/update/{userId}")
    @ResponseBody
    public String updateUser(@PathVariable Integer userId, @RequestParam String newLogin) {
        StandardUser user = (StandardUser) userService.getUserById(userId);
        user.setEmail(newLogin);
        userService.updateUser(user);
        return "Changed user: " + user;
    }

//    @RequestMapping(path = "/test-user", method = RequestMethod.GET)
//    @ResponseBody
//    public void tesUserCreate() {
//        User testUser = new StandardUser(0, "Test", "Testowy", "123");
//        this.createUser(testUser);
//        User user = this.getUser(new Integer(0));
//        log.info(user.getLogin(), user.getId(), user.getName());
//        userService.changeUserLogin(0, "Testowy2");
//    }
}
