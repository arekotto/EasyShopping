package com.easydevs.user;

import org.springframework.stereotype.Component;

/**
 * Created by arekotto on 09/12/2016.
 */
public interface User {

    Integer getId();
    String getName();
    String getLogin();
    String getPassword();

    void setId(Integer id);
    void setName(String userName);
    void setLogin(String login);
    void setPassword(String password);
}
