package com.easydevs.user.model;

import com.easydevs.user.UserType;

/**
 * Created by ibm on 2016-12-15.
 */
public class StandardUser implements User {

    private long id;
    private String name;
    private String email;
    private String street;
    private String country;
    private String city;
    private String token;
    private Long tokenValidationTimeStamp;
    private String emailVerificationToken;
    private boolean isEmailVerified = false;
    private boolean isAdmin;

    public StandardUser(){}

    public StandardUser(long id){
        this.id = id;
    }

    public StandardUser(long id,
                        String name,
                        String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) { this.city = city; }

    public void setCountry(String country) { this.country = country; }

    public void setTokenValidationStamp(Long timeStamp) {
        this.tokenValidationTimeStamp = timeStamp;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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

    public String getEmail() {
        return this.email;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public Long getTokenValidationTimeStamp() {
        return this.tokenValidationTimeStamp;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
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

}

