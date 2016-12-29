package com.easydevs.auth;

/**
 * Created by arekotto on 15/12/2016.
 */
public interface AuthenticationService {

    AuthenticationResult login(String userName, String password);

//    boolean isSessionActive(String userCookieId, String userCookieToken);

//    String generateToken();

    boolean isTokenValid(long userId, String token);

    boolean isPasswordFormatCorrect(String password);
}
