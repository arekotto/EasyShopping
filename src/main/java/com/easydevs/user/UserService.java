package com.easydevs.user;

/**
 * Created by arekotto on 09/12/2016.
 */
public interface UserService {

    User getUser(Integer userId);

    void createUser(User user);
}
