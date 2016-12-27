package com.easydevs.controller;

import com.easydevs.auth.AuthenticationResult;
import com.easydevs.auth.AuthenticationService;
import com.easydevs.user.UserService;
import com.easydevs.user.UserType;
import com.easydevs.user.command.UserCommand;
import com.easydevs.user.command.UserLoginCommand;
import com.easydevs.user.command.UserRegistrationCommand;
import com.easydevs.user.model.StandardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by arekotto on 09/12/2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String showRegister(Model model) {

        if(!model.containsAttribute("userRegistrationCommand")) {
            UserRegistrationCommand userRegistrationCommand = new UserRegistrationCommand();

            // default username for testing
            userRegistrationCommand.setName("here be username");
            model.addAttribute("userRegistrationCommand", userRegistrationCommand);
        }



        return "register";
    }

    @RequestMapping("/create")
    public String createNewUser(RedirectAttributes redirectAttributes,
                                @ModelAttribute("userRegistrationCommand") UserRegistrationCommand userRegistrationCommand) {

        if(userService.getUserByLogin(userRegistrationCommand.getLogin()) == null){
            StandardUser newUser = (StandardUser) userService.createNewUser(UserType.STANDARD);

            newUser.setLogin(userRegistrationCommand.getLogin());
            newUser.setName(userRegistrationCommand.getName());
            newUser.setPassword(userRegistrationCommand.getPassword());

            userService.updateUser(newUser);

            // nie zwracamy sciezki do jsp ale wywoluje url mapowany przez motede showUser
            return "redirect:userHomepage/" + newUser.getLogin();
        } else {
            userRegistrationCommand.setLoginUnavailable(true);
            redirectAttributes.addAttribute("userRegistrationCommand", userRegistrationCommand);
            return "redirect:register";

        }




    }

    @RequestMapping("/userHomepage/{login}")
    public String showUser(Model model,
                           @PathVariable String login,
                           HttpServletResponse response,
                           @CookieValue("id") String cookieId,
                           @CookieValue("token") String cookieToken) {

        if (userService.isTokenValid(Integer.parseInt(cookieId), cookieToken)) {
            StandardUser user = (StandardUser) userService.getUserByLogin(login);

            UserCommand userCommand = new UserCommand();
            userCommand.setName(user.getName());
            userCommand.setLogin(user.getLogin());

            model.addAttribute("userCommand", userCommand);

            return "/user_homepage";
        }

        return "redirect:login";


    }

    @RequestMapping("/login")
    public String showLogin(Model model) {
        if(!model.containsAttribute("userLoginCommand")) {
            model.addAttribute("userLoginCommand", new UserLoginCommand());
        }

        return "/user_login";
    }

    @RequestMapping("/authenticate")
    public String authenticateUser(
                Model model,
                HttpServletResponse response,
                @ModelAttribute("userLoginCommand")UserLoginCommand userLoginCommand,
                RedirectAttributes redirectAttributes) {

        AuthenticationResult authResult = authenticationService.login(userLoginCommand.getLogin(), userLoginCommand.getPassword());

        if (authResult.getSuccessful()) {

            StandardUser user = (StandardUser) userService.getUserByLogin(userLoginCommand.getLogin());

            user.setToken(userService.generateToken());
            user.setTokenValidationStamp(System.currentTimeMillis());

            userService.updateUser(user);

            response.addCookie(new Cookie("id", user.getId().toString()));
            response.addCookie(new Cookie("token", user.getToken()));

            return "redirect:userHomepage/" + user.getLogin();
        } else {
            userLoginCommand.setLoginFailed(true);
            redirectAttributes.addFlashAttribute("userLoginCommand", userLoginCommand);
            return "redirect:login";
        }
    }
}
