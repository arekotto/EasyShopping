package com.easydevs.user;

import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;

import java.util.List;

/**
 * Created by arekotto on 09/12/2016.
 */
public interface UserService {

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    User getUserById(long userId);

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    User getUserByEmail(String email);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<StandardUser> getAllUsers();

    /**
     * Create new user user.
     *
     * @param userType the user type
     * @return the user
     */
    User createNewUser(UserType userType);

    /**
     * Update user.
     *
     * @param user the user
     */
    void updateUser(User user);

    /**
     * Remove user.
     *
     * @param userId the user id
     */
    void removeUser(long userId);

    /**
     * Sets user admin.
     *
     * @param userId the user id
     * @param isAdmin should user be admin
     */
    void setUserAdmin(long userId, boolean isAdmin);

    /**
     * Gets new id for temp user.
     *
     * @return the new id for temp user
     */
    long getNewIdForTempUser();

}
