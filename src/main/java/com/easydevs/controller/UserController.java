package com.easydevs.controller;

import com.easydevs.auth.AuthenticationResult;
import com.easydevs.auth.AuthenticationService;
import com.easydevs.auth.Encryptor;
import com.easydevs.purchase.model.Cart;
import com.easydevs.purchase.service.CartService;
import com.easydevs.user.EmailVerificationService;
import com.easydevs.user.UserPasswordService;
import com.easydevs.user.UserService;
import com.easydevs.user.UserType;
import com.easydevs.user.command.*;
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

    /**
     * The constant JSP_PATH_PREFIX.
     */
    final static String JSP_PATH_PREFIX = "user/";

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserPasswordService userPasswordService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private CartService cartService;

    /**
     * Show register string.
     *
     * @param model           the model
     * @param response        the response
     * @param userIdCookie    the user id cookie
     * @param userTokenCookie the user token cookie
     * @return the string
     */
    @RequestMapping("/register")
    public String showRegister(Model model,
                               HttpServletResponse response,
                               @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                               @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {


        if(!model.containsAttribute("userRegistrationCommand")) {
            model.addAttribute("userRegistrationCommand", new UserRegistrationCommand());
        }

        return JSP_PATH_PREFIX + "user_register";
    }

    /**
     * Create new user string.
     *
     * @param redirectAttributes      the redirect attributes
     * @param response                the response
     * @param tempUserId              the temp user id
     * @param userRegistrationCommand the user registration command
     * @return the string
     */
    @RequestMapping("/create")
    public String createNewUser(RedirectAttributes redirectAttributes,
                                HttpServletResponse response,
                                @CookieValue(value = "tempId", defaultValue = "") String tempUserId,
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

            if (!tempUserId.isEmpty()) {
                long tempUserIdLong = Long.parseLong(tempUserId);
                Cart tempCart = cartService.getCartForUser(tempUserIdLong, true);
                if (tempCart != null) {
                    Cart newCart = cartService.createNewCart(newUser.getId(), false);
                    tempCart.getProductIdList().forEach(newCart::addToCart);
                    cartService.updateCartForUser(newUser.getId(), newCart);
                }
            }

            response.addCookie(createNewCookie("tempId", null));
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

    /**
     * Show edit string.
     *
     * @param model        the model
     * @param userIdCookie the user id cookie
     * @return the string
     */
    @RequestMapping("/edit")
    public String showEdit(Model model, @CookieValue(value = "id") String userIdCookie) {
        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userIdCookie));
        model.addAttribute("userStandardCommand", new UserStandardCommand(user));
        return JSP_PATH_PREFIX + "user_edit";
    }

    /**
     * Save string.
     *
     * @param userIdCookie        the user id cookie
     * @param userStandardCommand the user standard command
     * @return the string
     */
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

    /**
     * Show edit email string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/edit-email")
    public String showEditEmail(Model model) {
        if (!model.containsAttribute("userChangeEmailCommand")) {
            model.addAttribute("userChangeEmailCommand", new UserChangeEmailCommand());
        }
        return JSP_PATH_PREFIX + "user_edit_email";
    }

    /**
     * Save email string.
     *
     * @param userIdCookie           the user id cookie
     * @param redirectAttributes     the redirect attributes
     * @param userChangeEmailCommand the user change email command
     * @return the string
     */
    @RequestMapping("/save-email")
    public String saveEmail(@CookieValue(value = "id") String userIdCookie,
                            RedirectAttributes redirectAttributes,
                            @ModelAttribute("userChangeEmailCommand") UserChangeEmailCommand userChangeEmailCommand) {

        String newEmail = userChangeEmailCommand.getNewEmail();
        boolean isEmailUnavailable = userService.getUserByEmail(newEmail) != null;
        boolean isEmailFormatIncorrect = !authenticationService.isEmailFormatCorrect(newEmail);

        if (isEmailUnavailable) {
            userChangeEmailCommand.setErrorMessage("This email is already taken. Please use a different one.");
            redirectAttributes.addFlashAttribute("userChangeEmailCommand", userChangeEmailCommand);
            return "redirect:edit-email";
        }

        if (isEmailFormatIncorrect) {
            userChangeEmailCommand.setErrorMessage("The email you have entered seems to be of incorrect format.");
            redirectAttributes.addFlashAttribute("userChangeEmailCommand", userChangeEmailCommand);
            return "redirect:edit-email";
        }

        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userIdCookie));
        user.setEmailVerified(false);
        user.setEmail(newEmail);

        emailVerificationService.beginVerificationProcess(user);

        return "redirect:homepage";
    }


    /**
     * Show edit password string.
     *
     * @param model        the model
     * @param userIdCookie the user id cookie
     * @return the string
     */
    @RequestMapping("/edit-password")
    public String showEditPassword(Model model, @CookieValue(value = "id") String userIdCookie) {
        if (!model.containsAttribute("userChangePasswordCommand")) {
            model.addAttribute("userChangePasswordCommand", new UserChangePasswordCommand());
        }
        return JSP_PATH_PREFIX + "user_edit_password";
    }

    /**
     * Save password string.
     *
     * @param userIdCookie              the user id cookie
     * @param response                  the response
     * @param redirectAttributes        the redirect attributes
     * @param userChangePasswordCommand the user change password command
     * @return the string
     */
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


    /**
     * Show user string.
     *
     * @param model           the model
     * @param userIdCookie    the user id cookie
     * @param userTokenCookie the user token cookie
     * @return the string
     */
    @RequestMapping("/homepage")
    public String showUser(Model model,
                           @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                           @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {


        Long userId = new Long(userIdCookie);
        StandardUser user = (StandardUser) userService.getUserById(userId);
        model.addAttribute("userStandardCommand", new UserStandardCommand(user));

        return JSP_PATH_PREFIX + "user_homepage";
    }

    /**
     * Send new verification email string.
     *
     * @param userIdCookie the user id cookie
     * @return the string
     */
    @RequestMapping("send-new-verification-email")
    public String sendNewVerificationEmail(@CookieValue(value = "id", defaultValue = "") String userIdCookie) {
        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userIdCookie));
        if (user != null && !user.isEmailVerified()) {
            emailVerificationService.beginVerificationProcess(user);
        }
        return "redirect:homepage";
    }

    /**
     * Show login string.
     *
     * @param model           the model
     * @param response        the response
     * @param userIdCookie    the user id cookie
     * @param userTokenCookie the user token cookie
     * @return the string
     */
    @RequestMapping("/login")
    public String showLogin(Model model,
                            HttpServletResponse response,
                            @CookieValue(value = "id", defaultValue = "") String userIdCookie,
                            @CookieValue(value = "token", defaultValue = "") String userTokenCookie) {

        if(!model.containsAttribute("userLoginCommand")) {
            model.addAttribute("userLoginCommand", new UserLoginCommand());
        }

        return JSP_PATH_PREFIX + "user_login";
    }

    /**
     * Logout user string.
     *
     * @param model           the model
     * @param response        the response
     * @param userIdCookie    the user id cookie
     * @param userTokenCookie the user token cookie
     * @return the string
     */
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
        response.addCookie(createNewCookie("tempId", null));
        response.addCookie(createNewCookie("token", null));
        return "redirect:login";
    }

    /**
     * Authenticate user string.
     *
     * @param response           the response
     * @param tempUserId         the temp user id
     * @param userLoginCommand   the user login command
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping("/authenticate")
    public String authenticateUser(
            HttpServletResponse response,
            @CookieValue(value = "tempId", defaultValue = "") String tempUserId,
            @ModelAttribute("userLoginCommand")UserLoginCommand userLoginCommand,
            RedirectAttributes redirectAttributes) {

        AuthenticationResult authResult = authenticationService.login(userLoginCommand.getEmail(), userLoginCommand.getPassword());

        if (!authResult.getSuccessful()) {

            userLoginCommand.setIsLoginFailed(true);
            redirectAttributes.addFlashAttribute("userLoginCommand", userLoginCommand);
            return "redirect:login";

        }

        StandardUser user = (StandardUser) userService.getUserByEmail(userLoginCommand.getEmail());

        user.setToken(authResult.getToken());
        user.setTokenValidationStamp(System.currentTimeMillis());

        userService.updateUser(user);

        if (!tempUserId.isEmpty()) {
            long tempUserIdLong = Long.parseLong(tempUserId);
            Cart tempCart = cartService.getCartForUser(tempUserIdLong, true);
            if (tempCart != null) {
                Cart currentCart = cartService.getCartForUser(user.getId(), false);
                if (currentCart == null) {
                    currentCart = cartService.createNewCart(user.getId(), false);
                }
                tempCart.getProductIdList().forEach(currentCart::addToCart);
                cartService.updateCartForUser(user.getId(), currentCart);
            }
        }

        response.addCookie(createNewCookie("tempId", null));
        response.addCookie(createNewCookie("id", String.valueOf(user.getId())));
        response.addCookie(createNewCookie("token", user.getToken()));

        return "redirect:homepage";

    }

    /**
     * Verify email string.
     *
     * @param model             the model
     * @param userId            the user id
     * @param verificationToken the verification token
     * @return the string
     */
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

        return JSP_PATH_PREFIX + "user_email_verification";
    }

    private Cookie createNewCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        return cookie;
    }
}
