package com.easydevs.user.model;

import com.easydevs.user.UserType;

/**
 * Created by arekotto on 21/12/2016.
 */
public class TempUser implements User {

    private Integer id;
    private String token;

    public TempUser(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public UserType getUserType() {
        return UserType.TEMP;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
