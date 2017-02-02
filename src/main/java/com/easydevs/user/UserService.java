package com.easydevs.user;

import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;

import java.util.List;

/**
 * Created by arekotto on 09/12/2016.
 */
public interface UserService {

    User getUserById(long userId);

    User getUserByEmail(String email);

    List<StandardUser> getAllUsers();

    User createNewUser(UserType userType);

    void updateUser(User user);

}
