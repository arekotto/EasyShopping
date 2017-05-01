package com.easydevs.controller;

import com.easydevs.auth.AuthenticationResult;
import com.easydevs.auth.AuthenticationService;
import com.easydevs.auth.Encryptor;
import com.easydevs.user.EmailVerificationService;
import com.easydevs.user.UserPasswordService;
import com.easydevs.user.UserService;
import com.easydevs.user.UserType;
import com.easydevs.user.command.UserChangePasswordCommand;
import com.easydevs.user.command.UserLoginCommand;
import com.easydevs.user.command.UserRegistrationCommand;
import com.easydevs.user.command.UserStandardCommand;
import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private EmailVerificationService emailVerificationService;

    @RequestMapping("/register")
    public String showRegister(Model model,
                               HttpServletResponse response,
                               @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                               @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {


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

            emailVerificationService.beginVerificationProcess(newUser);

            response.addCookie(createNewCookie("id", String.valueOf(newUser.getId())));
            response.addCookie(createNewCookie("token", newUser.getToken()));
            return "redirect:homepage";
        }


        userRegistrationCommand.setIsPasswordFormatIncorrect(isPasswordIncorrect);
        userRegistrationCommand.setIsLoginUnavailable(isEmailUnavailable);
        userRegistrationCommand.setIsEmailIncorrect(isEmailFormatIncorrect);
        redirectAttributes.addFlashAttribute("userRegistrationCommand", userRegistrationCommand);
        return "redirect:register";
    }

    @RequestMapping("/edit")
    public String showEdit(Model model, @CookieValue(value = "id") String userIdCookie) {
        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userIdCookie));
        model.addAttribute("userStandardCommand", new UserStandardCommand(user));
        return "/user_edit";
    }

    @RequestMapping("/save")
    public String save(@CookieValue(value = "id") String userIdCookie,
                       @ModelAttribute("userStandardCommand") UserStandardCommand userStandardCommand) {

        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userIdCookie));
        user.setCity(userStandardCommand.getCity());
        user.setCountry(userStandardCommand.getCountry());
        user.setStreet(userStandardCommand.getStreet());
        user.setName(userStandardCommand.getName());
        userService.updateUser(user);

        return "redirect:homepage";
    }

    @RequestMapping("/edit-password")
    public String showEditEmail(Model model, @CookieValue(value = "id") String userIdCookie) {
        if (!model.containsAttribute("userChangePasswordCommand")) {
            model.addAttribute("userChangePasswordCommand", new UserChangePasswordCommand());
        }
        return "/user_edit_password";
    }

    @RequestMapping("/save-password")
    public String savePassword(@CookieValue(value = "id") String userIdCookie,
                               HttpServletResponse response,
                               RedirectAttributes redirectAttributes,
                       @ModelAttribute("userChangePasswordCommand") UserChangePasswordCommand userChangePasswordCommand) {

        if (!userChangePasswordCommand.getNewPassword().equals(userChangePasswordCommand.getNewPasswordRetyped())) {
            UserChangePasswordCommand newUserChangePasswordCommand = new UserChangePasswordCommand();
            newUserChangePasswordCommand.setErrorMessage("Passwords don't match.");
            redirectAttributes.addFlashAttribute("userChangePasswordCommand", newUserChangePasswordCommand);
            return "redirect:edit-password";
        }
        if (!authenticationService.isPasswordFormatCorrect(userChangePasswordCommand.getNewPassword())) {
            UserChangePasswordCommand newUserChangePasswordCommand = new UserChangePasswordCommand();
            newUserChangePasswordCommand.setErrorMessage("The new password is too short.");
            redirectAttributes.addFlashAttribute("userChangePasswordCommand", newUserChangePasswordCommand);
            return "redirect:edit-password";
        }

        Long userId = Long.parseLong(userIdCookie);
        StandardUser user = (StandardUser) userService.getUserById(userId);
        AuthenticationResult authResult = authenticationService.login(user.getEmail(), userChangePasswordCommand.getCurrentPassword());
        if(!authResult.getSuccessful()) {
            UserChangePasswordCommand newUserChangePasswordCommand = new UserChangePasswordCommand();
            newUserChangePasswordCommand.setErrorMessage("The password you have entered is incorrect.");
            redirectAttributes.addFlashAttribute("userChangePasswordCommand", newUserChangePasswordCommand);
            return "redirect:edit-password";
        }
        userPasswordService.insertOrUpdatePassword(
                userId,
                new Encryptor().encryptWithMD5(userChangePasswordCommand.getNewPassword()));

        authResult = authenticationService.login(user.getEmail(), userChangePasswordCommand.getNewPassword());
        user.setToken(authResult.getToken());
        user.setTokenValidationStamp(System.currentTimeMillis());
        userService.updateUser(user);

        response.addCookie(createNewCookie("token", authResult.getToken()));

        return "redirect:homepage";
    }


    @RequestMapping("/homepage")
    public String showUser(Model model,
                           @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                           @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {


        Long userId = new Long(userIdCookie);
        StandardUser user = (StandardUser) userService.getUserById(userId);
        model.addAttribute("userStandardCommand", new UserStandardCommand(user));

        return "/user_homepage";
    }

    @RequestMapping("send-new-verification-email")
    public String sendNewVerificationEmail(@CookieValue(value = "id", defaultValue = "") String userIdCookie) {
        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userIdCookie));
        if (user != null && !user.isEmailVerified()) {
            emailVerificationService.beginVerificationProcess(user);
        }
        return "redirect:homepage";
    }

    @RequestMapping("/login")
    public String showLogin(Model model,
                            HttpServletResponse response,
                            @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                            @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {

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

        Long userId = new Long(userIdCookie);

        StandardUser user = (StandardUser) userService.getUserById(userId);
        if (user != null) {
            user.setToken(null);
            user.setTokenValidationStamp(null);
            userService.updateUser(user);
        }
        response.addCookie(createNewCookie("id", null));
        response.addCookie(createNewCookie("token", null));
        return "redirect:login";
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

            response.addCookie(createNewCookie("id", String.valueOf(user.getId())));
            response.addCookie(createNewCookie("token", user.getToken()));

            return "redirect:homepage";
        } else {
            userLoginCommand.setIsLoginFailed(true);
            redirectAttributes.addFlashAttribute("userLoginCommand", userLoginCommand);
            return "redirect:login";
        }
    }

    @RequestMapping("/verify-email")
    public String verifyEmail(Model model,
                              @RequestParam Long userId,
                              @RequestParam String verificationToken) {

        StandardUser user = (StandardUser) userService.getUserById(userId);
        if (user != null) {
            if (user.isEmailVerified()) {
                model.addAttribute("isEmailVerified", false);
                model.addAttribute("isEmailAlreadyVerified", true);
            } else {
                if (user.getEmailVerificationToken().equals(verificationToken)) {
                    user.setEmailVerified(true);
                    userService.updateUser(user);
                    model.addAttribute("isEmailVerified", true);
                    model.addAttribute("isEmailAlreadyVerified", false);

                }
            }
        } else {
            model.addAttribute("isEmailVerified", false);
            model.addAttribute("isEmailAlreadyVerified", false);

        }

        return "user_email_verification";
    }

    private Cookie createNewCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        return cookie;
    }
}
