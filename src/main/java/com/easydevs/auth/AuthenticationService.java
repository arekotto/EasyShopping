package com.easydevs.auth;

/**
 * Created by arekotto on 15/12/2016.
 */
public interface AuthenticationService {

    AuthenticationResult login(String userName, String password);

    boolean isTokenValid(String token);
}
