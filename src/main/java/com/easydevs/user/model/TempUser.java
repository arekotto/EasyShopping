package com.easydevs.user.model;

import com.easydevs.user.UserType;

/**
 * Created by arekotto on 21/12/2016.
 */
public class TempUser implements User {

    private long id;
    private String token;
    private Long tokenValidationTimestamp;

    public TempUser(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
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

    @Override
    public Long getTokenValidationTimeStamp() {
        return tokenValidationTimestamp;
    }

    @Override
    public void setTokenValidationTimeStamp(long timestamp) {
        this. tokenValidationTimestamp = timestamp;
    }
}
