package com.easydevs.user.command;

/**
 * Created by arekotto on 16/12/2016.
 */
public class UserRegistrationCommand {
    private String password;
    private String name;
    private String email;
    private String street;
    private String city;
    private String country;

    private boolean isEmailUnavailable = false;
    private boolean isPasswordFormatIncorrect = false;
    private boolean isEmailIncorrect = false;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsLoginUnavailable() {
        return isEmailUnavailable;
    }

    public void setIsLoginUnavailable(boolean loginUnavailable) {
        isEmailUnavailable = loginUnavailable;
    }

    public boolean getIsPasswordFormatIncorrect() {
        return isPasswordFormatIncorrect;
    }

    public void setIsPasswordFormatIncorrect(boolean isPasswordFormatIncorrect) {
        this.isPasswordFormatIncorrect = isPasswordFormatIncorrect;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsEmailIncorrect() {
        return isEmailIncorrect;
    }

    public void setIsEmailIncorrect(boolean EmailIncorrect) {
        isEmailIncorrect = EmailIncorrect;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
