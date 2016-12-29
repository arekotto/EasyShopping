package com.easydevs.controller;

import com.easydevs.auth.AuthenticationResult;
import com.easydevs.auth.AuthenticationService;
import com.easydevs.auth.Encryptor;
import com.easydevs.user.UserPasswordService;
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

    @Autowired
    private UserPasswordService userPasswordService;

    @RequestMapping("/register")
    public String showRegister(Model model,
                               HttpServletResponse response,
                               @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                               @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {

        // check if user already logged in
        if (!userIdCookie.isEmpty() && !userTokenCookie.isEmpty()) {
            Integer userId = new Integer(userIdCookie);

            if(authenticationService.isTokenValid(userId, userTokenCookie)){
                return "redirect:userHomepage";
            } else {
                response.addCookie(new Cookie("id", null));
                response.addCookie(new Cookie("token", null));
            }
        }

        // user not logged in
        if(!model.containsAttribute("userRegistrationCommand")) {
            UserRegistrationCommand userRegistrationCommand = new UserRegistrationCommand();
            userRegistrationCommand.setLogin("here be username");
            model.addAttribute("userRegistrationCommand", userRegistrationCommand);
        }

        return "register";
    }

    @RequestMapping("/create")
    public String createNewUser(RedirectAttributes redirectAttributes,
                                HttpServletResponse response,
                                @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                                @CookieValue(value = "token", defaultValue = "") String userTokenCookie,
                                @ModelAttribute("userRegistrationCommand") UserRegistrationCommand userRegistrationCommand) {

        // check if user already logged in
        if (!userIdCookie.isEmpty() && !userTokenCookie.isEmpty()) {
            Integer userId = new Integer(userIdCookie);

            if(authenticationService.isTokenValid(userId, userTokenCookie)){
                return "redirect:userHomepage";
            } else {
                response.addCookie(new Cookie("id", null));
                response.addCookie(new Cookie("token", null));
            }
        }
        boolean isLoginUnavailable = (userService.getUserByLogin(userRegistrationCommand.getLogin()) != null);
        boolean isPasswordIncorrect = !authenticationService.isPasswordFormatCorrect(userRegistrationCommand.getPassword());
        boolean isEmailFormatIncorrect = !authenticationService.isEmailFormatCorrect(userRegistrationCommand.getEmail());

        if(!isLoginUnavailable && !isPasswordIncorrect && !isEmailFormatIncorrect){
            StandardUser newUser = (StandardUser) userService.createNewUser(UserType.STANDARD);

            userPasswordService.insertOrUpdatePassword(newUser.getId(), new Encryptor().encryptWithMD5(userRegistrationCommand.getPassword()));

            newUser.setLogin(userRegistrationCommand.getLogin());
            newUser.setName(userRegistrationCommand.getName());
            newUser.setEmail(userRegistrationCommand.getEmail());
            userService.updateUser(newUser);

            AuthenticationResult authResult = authenticationService.login(newUser.getLogin(), userRegistrationCommand.getPassword());

            newUser.setToken(authResult.getToken());
            newUser.setTokenValidationStamp(System.currentTimeMillis());

            userService.updateUser(newUser);

            response.addCookie(new Cookie("id", String.valueOf(newUser.getId())));
            response.addCookie(new Cookie("token", newUser.getToken()));
            return "redirect:userHomepage";
        }


        userRegistrationCommand.setIsPasswordFormatIncorrect(isPasswordIncorrect);
        userRegistrationCommand.setIsLoginUnavailable(isLoginUnavailable);
        userRegistrationCommand.setIsEmailIncorrect(isEmailFormatIncorrect);
        redirectAttributes.addFlashAttribute("userRegistrationCommand", userRegistrationCommand);
        return "redirect:register";

    }

    @RequestMapping("/userHomepage")
    public String showUser(Model model,
                           HttpServletResponse response,
                           @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                           @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {

        if (!userIdCookie.isEmpty() && !userTokenCookie.isEmpty()) {
            Long userId = new Long(userIdCookie);

            if(authenticationService.isTokenValid(userId, userTokenCookie)){
                StandardUser user = (StandardUser) userService.getUserById(userId);

                UserCommand userCommand = new UserCommand();
                userCommand.setName(user.getName());
                userCommand.setLogin(user.getLogin());

                model.addAttribute("userCommand", userCommand);

                return "/user_homepage";
            } else {
                response.addCookie(new Cookie("id", null));
                response.addCookie(new Cookie("token", null));
            }
        }

        return "redirect:login";


    }

    @RequestMapping("/login")
    public String showLogin(Model model,
                            HttpServletResponse response,
                            @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                            @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {

        // check if user already logged in
        if (!userIdCookie.isEmpty() && !userTokenCookie.isEmpty()) {
            Long userId = new Long(userIdCookie);

            if(authenticationService.isTokenValid(userId, userTokenCookie)){
                return "redirect:userHomepage";
            } else {
                response.addCookie(new Cookie("id", null));
                response.addCookie(new Cookie("token", null));
            }
        }

        if(!model.containsAttribute("userLoginCommand")) {
            model.addAttribute("userLoginCommand", new UserLoginCommand());
        }

        return "/user_login";
    }

    @RequestMapping("/logout")
    public String logoutUser(Model model,
                            HttpServletResponse response,
                            @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                            @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {

        // check if user already logged in
        if (!userIdCookie.isEmpty() && !userTokenCookie.isEmpty()) {
            Integer userId = new Integer(userIdCookie);

            StandardUser user = (StandardUser) userService.getUserById(userId);
            if (user != null) {
                user.setToken(null);
                user.setTokenValidationStamp(null);
                userService.updateUser(user);
            }
            response.addCookie(new Cookie("id", null));
            response.addCookie(new Cookie("token", null));
        }

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

            user.setToken(authResult.getToken());
            user.setTokenValidationStamp(System.currentTimeMillis());

            userService.updateUser(user);

            response.addCookie(new Cookie("id", String.valueOf(user.getId())));
            response.addCookie(new Cookie("token", user.getToken()));

            return "redirect:userHomepage";
        } else {
            userLoginCommand.setIsLoginFailed(true);
            redirectAttributes.addFlashAttribute("userLoginCommand", userLoginCommand);
            return "redirect:login";
        }
    }
}
