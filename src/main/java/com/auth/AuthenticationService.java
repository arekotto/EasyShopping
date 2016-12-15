package com.auth;

/**
 * Created by arekotto on 15/12/2016.
 */
public interface AuthenticationService {

    AuthenticationResult login(String userName, String password);

    Boolean isTokenValid(String token);
}
