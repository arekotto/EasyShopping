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


    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets is login unavailable.
     *
     * @return the is login unavailable
     */
    public boolean getIsLoginUnavailable() {
        return isEmailUnavailable;
    }

    /**
     * Sets is login unavailable.
     *
     * @param loginUnavailable the login unavailable
     */
    public void setIsLoginUnavailable(boolean loginUnavailable) {
        isEmailUnavailable = loginUnavailable;
    }

    /**
     * Gets is password format incorrect.
     *
     * @return the is password format incorrect
     */
    public boolean getIsPasswordFormatIncorrect() {
        return isPasswordFormatIncorrect;
    }

    /**
     * Sets is password format incorrect.
     *
     * @param isPasswordFormatIncorrect the is password format incorrect
     */
    public void setIsPasswordFormatIncorrect(boolean isPasswordFormatIncorrect) {
        this.isPasswordFormatIncorrect = isPasswordFormatIncorrect;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets is email incorrect.
     *
     * @return the is email incorrect
     */
    public boolean getIsEmailIncorrect() {
        return isEmailIncorrect;
    }

    /**
     * Sets is email incorrect.
     *
     * @param EmailIncorrect the email incorrect
     */
    public void setIsEmailIncorrect(boolean EmailIncorrect) {
        isEmailIncorrect = EmailIncorrect;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }


}
