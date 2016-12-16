package com.easydevs.user;

/**
 * Created by arekotto on 09/12/2016.
 */
public interface UserService {

    User getUser(Integer userId);

    User getUserByLogin(String login);

    void createUser(User newUser);

    void changeUserName(Integer userId, String newName);

    void changeUserLogin(Integer userId, String newLogin);

    void updateUser(User user);

    void updateTokenTimeStamp(Integer userId, Long timeStamp);

//    void changeUserPassword(Integer userId, String newPassword)
}
