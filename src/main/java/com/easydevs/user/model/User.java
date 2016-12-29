package com.easydevs.user.model;

import com.easydevs.user.UserType;

/**
 * Created by arekotto on 09/12/2016.
 */
public interface User {

    long getId();

    UserType getUserType();

    String getToken();

    void setToken(String token);

    Long getTokenValidationTimeStamp();

    void setTokenValidationTimeStamp(long timestamp);
}
