package com.easydevs.controller;

import com.easydevs.user.StandardUser;
import com.easydevs.user.User;
import com.easydevs.user.UserService;
import com.easydevs.user.model.UserCommand;
import com.easydevs.user.model.UserRegistrationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by arekotto on 09/12/2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String showRegister(Model model) {

        UserRegistrationCommand userRegistrationCommand = new UserRegistrationCommand();

        // default username for testing
        userRegistrationCommand.setName("here be username");
        model.addAttribute("userRegistrationCommand", userRegistrationCommand);

        return "register";
    }

    @RequestMapping("/create")
    public String createNewUser(Model model, @ModelAttribute("userRegistrationCommand") UserRegistrationCommand userRegistrationCommand) {

        User newUser = new StandardUser(ThreadLocalRandom.current().nextInt(),
                userRegistrationCommand.getName(),
                userRegistrationCommand.getLogin(),
                userRegistrationCommand.getPassword());

        userService.createUser(newUser);


        // nie zwracamy sciezki do jsp ale wywoluje url mapowany przez motede showUser
        return "redirect:userHomepage/" + newUser.getLogin();
    }

    @RequestMapping("/userHomepage/{login}")
    public String showUser(Model model, @PathVariable String login) {

        User user = userService.getUserByLogin(login);

        UserCommand userCommand = new UserCommand();
        userCommand.setName(user.getName());
        userCommand.setLogin(user.getLogin());

        model.addAttribute("userCommand", userCommand);

        return "/user_homepage";
    }
}
