package com.easydevs.user.command;

import com.easydevs.user.model.StandardUser;

/**
 * Created by arekotto on 07/01/2017.
 */
public class UserStandardCommand {
    private String name;
    private String email;
    private String street;
    private String country;
    private String city;
    private boolean isEmailVerified;
    private boolean isAdmin;

    public UserStandardCommand() {
    }

    public UserStandardCommand(StandardUser standardUser) {
        name = standardUser.getName();
        email = standardUser.getEmail();
        street = standardUser.getStreet();
        country = standardUser.getCountry();
        city = standardUser.getCity();
        isEmailVerified = standardUser.isEmailVerified();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
}
