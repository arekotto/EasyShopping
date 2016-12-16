package com.easydevs.auth;

import com.easydevs.user.User;
import com.easydevs.user.UserService;
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

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private SecureRandom random = new SecureRandom();
    private final int TOKEN_LENGTH = 130;

    @Override
    public AuthenticationResult login(String login, String password) {
        User user = userService.getUserByLogin(login);
        if (user.getPassword().equals(password)) {
            log.info("Login successful: " + login);
            return new AuthenticationResult(true, generateToken());
        } else {
            log.info("Login unsuccessful: " + login);
            return new AuthenticationResult(false, null);
        }
    }

    @Override
    public boolean isTokenValid(String token) {
        return false;
    }

    private String generateToken() {
        return new BigInteger(TOKEN_LENGTH, random).toString(32);
    }
}
