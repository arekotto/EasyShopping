package com.easydevs.user;

/**
 * Created by arekotto on 27/12/2016.
 */
public interface UserPasswordService {

    String getPasswordHash(long userId);

    void insertOrUpdatePassword(long userId, String password);

    void deletePassword(long userId);
}
