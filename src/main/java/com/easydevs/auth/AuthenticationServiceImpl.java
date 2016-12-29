package com.easydevs.auth;

import com.easydevs.user.UserPasswordService;
import com.easydevs.user.UserService;
import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by arekotto on 15/12/2016.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPasswordService userPasswordService;

    private Encryptor encryptor = new Encryptor();

    private Logger log = LoggerFactory.getLogger(this.getClass());


    private SecureRandom random = new SecureRandom();

    private final int TOKEN_LENGTH = 130;
    private final long TOKEN_VALIDATION_PERIOD = 1000 * 60 * 60 * 24;

    @Override
    public AuthenticationResult login(String login, String password) {
        StandardUser user = (StandardUser) userService.getUserByEmail(login);

        if (user != null && login != null && password != null) {
            String realPasswordHash = userPasswordService.getPasswordHash(user.getId());
            if (realPasswordHash != null && encryptor.encryptWithMD5(password).equals(realPasswordHash)) {
                log.info("Login successful: " + login);

                user.setToken(generateToken());
                user.setTokenValidationStamp(System.currentTimeMillis());
                userService.updateUser(user);

                return new AuthenticationResult(true, generateToken());
            }


        }
        log.info("Login unsuccessful: " + login);
        return new AuthenticationResult(false, null);

    }

//    public boolean isSessionActive(String userCookieId, String userCookieToken){
//        if (userCookieId != null && userCookieToken != null) {
//            Integer userId = Integer.parseInt(userCookieId);
//            return isTokenValid(userId, userCookieToken);
//        }
//        return false;
//    }

    @Override
    public boolean isTokenValid(long userId, String token) {
        User user = userService.getUserById(userId);
        if (user != null) {
            Long tokenTimeStamp = user.getTokenValidationTimeStamp();
            if (tokenTimeStamp != null) {
                if (System.currentTimeMillis() - TOKEN_VALIDATION_PERIOD < tokenTimeStamp) {
                    return user.getToken().equals(token);
                }
            }
        }

        return false;
    }

    private String generateToken() {
        return new BigInteger(TOKEN_LENGTH, random).toString(32);
    }

    @Override
    public boolean isPasswordFormatCorrect(String password) {
        // TODO make some more rules for password format
        return password.length() >= 5;
    }


    @Override
    public boolean isEmailFormatCorrect(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
