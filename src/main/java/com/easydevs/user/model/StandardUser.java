package com.easydevs.user.model;

import com.easydevs.user.UserType;

/**
 * Created by ibm on 2016-12-15.
 */
public class StandardUser implements User {

    private long id;
    private String name;
    private String login;
    private String email;
    private String token;
    private Long tokenValidationTimeStamp;

    public StandardUser(){}

    public StandardUser(long id){
        this.id = id;
    }

    public StandardUser(long id,
                        String name,
                        String login) {
        this.id = id;
        this.name = name;
        this.login = login;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTokenValidationStamp(Long timeStamp) {
        this.tokenValidationTimeStamp = timeStamp;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public UserType getUserType() {
        return UserType.STANDARD;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getEmail() {
        return this.email;
    }

    public Long getTokenValidationTimeStamp() {
        return this.tokenValidationTimeStamp;
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
    public void setTokenValidationTimeStamp(long timestamp) {
        this.tokenValidationTimeStamp = timestamp;
    }

    @Override
    public String toString() {
        return "StandardUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", tokenValidationTimeStamp=" + tokenValidationTimeStamp +
                '}';
    }
}

