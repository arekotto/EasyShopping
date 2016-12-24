package com.easydevs.auth;

import com.easydevs.user.UserService;
import com.easydevs.user.model.StandardUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by arekotto on 15/12/2016.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public AuthenticationResult login(String login, String password) {
        StandardUser user = (StandardUser) userService.getUserByLogin(login);
        if (user.getPassword().equals(password)) {

            log.info("Login successful: " + login);

            user.setToken(userService.generateToken());
            user.setTokenValidationStamp(System.currentTimeMillis());
            userService.updateUser(user);

            return new AuthenticationResult(true, userService.generateToken());
        } else {
            log.info("Login unsuccessful: " + login);
            return new AuthenticationResult(false, null);
        }
    }
}
