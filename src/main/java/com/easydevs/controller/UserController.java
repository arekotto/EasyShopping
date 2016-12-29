package com.easydevs.controller;

import com.easydevs.auth.AuthenticationResult;
import com.easydevs.auth.AuthenticationService;
import com.easydevs.auth.Encryptor;
import com.easydevs.support.HeaderCommand;
import com.easydevs.user.UserPasswordService;
import com.easydevs.user.UserService;
import com.easydevs.user.UserType;
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

        if (checkUserAndManageCookies(response, userIdCookie, userTokenCookie)) {
            return "redirect:userHomepage";
        }

        if(!model.containsAttribute("userRegistrationCommand")) {
            model.addAttribute("userRegistrationCommand", new UserRegistrationCommand());
        }

        return "user_register";
    }

    @RequestMapping("/create")
    public String createNewUser(RedirectAttributes redirectAttributes,
                                HttpServletResponse response,
                                @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                                @CookieValue(value = "token", defaultValue = "") String userTokenCookie,
                                @ModelAttribute("userRegistrationCommand") UserRegistrationCommand userRegistrationCommand) {

        if (checkUserAndManageCookies(response, userIdCookie, userTokenCookie)) {
            return "redirect:userHomepage";
        }

        boolean isEmailUnavailable = (userService.getUserByEmail(userRegistrationCommand.getEmail()) != null);
        boolean isPasswordIncorrect = !authenticationService.isPasswordFormatCorrect(userRegistrationCommand.getPassword());
        boolean isEmailFormatIncorrect = !authenticationService.isEmailFormatCorrect(userRegistrationCommand.getEmail());


        if(!isEmailUnavailable && !isPasswordIncorrect && !isEmailFormatIncorrect){
            StandardUser newUser = (StandardUser) userService.createNewUser(UserType.STANDARD);

            userPasswordService.insertOrUpdatePassword(newUser.getId(), new Encryptor().encryptWithMD5(userRegistrationCommand.getPassword()));

            newUser.setName(userRegistrationCommand.getName());
            newUser.setEmail(userRegistrationCommand.getEmail());
            newUser.setStreet(userRegistrationCommand.getStreet());
            newUser.setCity(userRegistrationCommand.getCity());
            newUser.setCountry(userRegistrationCommand.getCountry());
            userService.updateUser(newUser);

            AuthenticationResult authResult = authenticationService.login(newUser.getEmail(), userRegistrationCommand.getPassword());

            newUser.setToken(authResult.getToken());
            newUser.setTokenValidationStamp(System.currentTimeMillis());

            userService.updateUser(newUser);

            response.addCookie(getCookie("id", String.valueOf(newUser.getId())));
            response.addCookie(getCookie("token", newUser.getToken()));
            return "redirect:userHomepage";
        }


        userRegistrationCommand.setIsPasswordFormatIncorrect(isPasswordIncorrect);
        userRegistrationCommand.setIsLoginUnavailable(isEmailUnavailable);
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

                HeaderCommand headerCommand = new HeaderCommand();
                headerCommand.setUserName(user.getName());
                headerCommand.setUserEmail(user.getEmail());

                model.addAttribute("headerCommand", headerCommand);

                return "/user_homepage";
            } else {
                response.addCookie(getCookie("id", null));
                response.addCookie(getCookie("token", null));
            }
        }

        return "redirect:login";


    }

    @RequestMapping("/login")
    public String showLogin(Model model,
                            HttpServletResponse response,
                            @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                            @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {

        if (checkUserAndManageCookies(response, userIdCookie, userTokenCookie)) {
            return "redirect:userHomepage";
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

        if (!userIdCookie.isEmpty() && !userTokenCookie.isEmpty()) {
            Integer userId = new Integer(userIdCookie);

            StandardUser user = (StandardUser) userService.getUserById(userId);
            if (user != null) {
                user.setToken(null);
                user.setTokenValidationStamp(null);
                userService.updateUser(user);
            }
            response.addCookie(getCookie("id", null));
            response.addCookie(getCookie("token", null));
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

        AuthenticationResult authResult = authenticationService.login(userLoginCommand.getEmail(), userLoginCommand.getPassword());

        if (authResult.getSuccessful()) {

            StandardUser user = (StandardUser) userService.getUserByEmail(userLoginCommand.getEmail());

            user.setToken(authResult.getToken());
            user.setTokenValidationStamp(System.currentTimeMillis());

            userService.updateUser(user);

            response.addCookie(getCookie("id", String.valueOf(user.getId())));
            response.addCookie(getCookie("token", user.getToken()));

            return "redirect:userHomepage";
        } else {
            userLoginCommand.setIsLoginFailed(true);
            redirectAttributes.addFlashAttribute("userLoginCommand", userLoginCommand);
            return "redirect:login";
        }
    }

    private boolean checkUserAndManageCookies(HttpServletResponse response, String userIdCookie, String userTokenCookie) {
        if (!userIdCookie.isEmpty() && !userTokenCookie.isEmpty()) {
            Integer userId = new Integer(userIdCookie);

            if(authenticationService.isTokenValid(userId, userTokenCookie)){
                return true;
            } else {
                response.addCookie(getCookie("id", null));
                response.addCookie(getCookie("token", null));
            }
        }
        return false;
    }

    private Cookie getCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        return cookie;
    }


}
