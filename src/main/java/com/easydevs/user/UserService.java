package com.easydevs.user;

import com.easydevs.user.model.User;

/**
 * Created by arekotto on 09/12/2016.
 */
public interface UserService {

    User getUserById(Integer userId);

    User getUserByLogin(String login);

    User createNewUser(UserType userType);

    void updateUser(User user);

    String generateToken();

    boolean isTokenValid(Integer userId, String token);

    boolean isPasswordFormatCorrect(String password);

}
