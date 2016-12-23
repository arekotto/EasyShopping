package com.easydevs.controller;

import com.easydevs.user.UserService;
import com.easydevs.user.UserType;
import com.easydevs.user.command.UserCommand;
import com.easydevs.user.command.UserRegistrationCommand;
import com.easydevs.user.model.StandardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

        StandardUser newUser = (StandardUser) userService.createNewUser(UserType.STANDARD);

        newUser.setLogin(userRegistrationCommand.getLogin());
        newUser.setName(userRegistrationCommand.getName());
        newUser.setPassword(userRegistrationCommand.getPassword());

        userService.updateUser(newUser);


        // nie zwracamy sciezki do jsp ale wywoluje url mapowany przez motede showUser
        return "redirect:userHomepage/" + newUser.getLogin();
    }

    @RequestMapping("/userHomepage/{login}")
    public String showUser(Model model, @PathVariable String login) {

        StandardUser user = (StandardUser) userService.getUserByLogin(login);

        UserCommand userCommand = new UserCommand();
        userCommand.setName(user.getName());
        userCommand.setLogin(user.getLogin());

        model.addAttribute("userCommand", userCommand);

        return "/user_homepage";
    }
}
