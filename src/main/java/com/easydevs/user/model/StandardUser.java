package com.easydevs.user.model;

import com.easydevs.user.UserType;

/**
 * Created by ibm on 2016-12-15.
 */
public class StandardUser implements User {

    private Integer id;
    private String name;
    private String login;
    private String password;

    private Long tokenValidationTimeStamp;

    public StandardUser(){}

    public StandardUser(Integer id){
        this.id = id;
    }

    public StandardUser(Integer id,
                        String name,
                        String login,
                        String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTokenValidationStamp(Long timeStamp) {
        this.tokenValidationTimeStamp = timeStamp;
    }

    @Override
    public Integer getId() {
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

    public String getPassword() {
        return password;
    }

    public Long getTokenValidationTimeStamp() {
        return this.tokenValidationTimeStamp;
    }

    @Override
    public String toString() {
        return "StandardUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", tokenValidationTimeStamp=" + tokenValidationTimeStamp +
                '}';
    }
}

