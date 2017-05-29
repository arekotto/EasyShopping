package com.easydevs.user;

import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by arekotto on 28/01/2017.
 */
@Service
public class EmailVerificationService {

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * The Email service.
     */
    @Autowired
    EmailService emailService;

    private SecureRandom random = new SecureRandom();
    private int VERIFICATION_TOKEN_LENGTH = 100;

    /**
     * Begin verification process.
     *
     * @param user the user
     */
    public void beginVerificationProcess(StandardUser user) {
//        StandardUser user = (StandardUser) userService.getUserById(userId);
        if (user != null && !user.isEmailVerified()) {
            String verificationToken = generateVerificationToken();
            user.setEmailVerificationToken(verificationToken);
            userService.updateUser(user);
            emailService.sendVerificationEmail(user.getId(), user.getEmail(), verificationToken);
        }
    }

    private String generateVerificationToken() {
        return new BigInteger(VERIFICATION_TOKEN_LENGTH, random).toString(32);
    }

}
