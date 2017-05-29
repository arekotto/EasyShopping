package com.easydevs.auth;

/**
 * Created by arekotto on 15/12/2016.
 */
public interface AuthenticationService {

    /**
     * Login authentication result.
     *
     * @param userName the user name
     * @param password the password
     * @return the authentication result
     */
    AuthenticationResult login(String userName, String password);

//    boolean isSessionActive(String userCookieId, String userCookieToken);

//    String generateToken();

    /**
     * Is token valid boolean.
     *
     * @param userId the user id
     * @param token  the token
     * @return the boolean
     */
    boolean isTokenValid(long userId, String token);

    /**
     * Is password format correct boolean.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isPasswordFormatCorrect(String password);

    /**
     * Is email format correct boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isEmailFormatCorrect(String email);
}
