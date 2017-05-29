package com.easydevs.user;

/**
 * Created by arekotto on 27/12/2016.
 */
public interface UserPasswordService {

    /**
     * Gets password hash.
     *
     * @param userId the user id
     * @return the password hash
     */
    String getPasswordHash(long userId);

    /**
     * Insert or update password.
     *
     * @param userId   the user id
     * @param password the password
     */
    void insertOrUpdatePassword(long userId, String password);

    /**
     * Delete password.
     *
     * @param userId the user id
     */
    void deletePassword(long userId);
}
