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

    /**
     * Instantiates a new User standard command.
     */
    public UserStandardCommand() {
    }

    /**
     * Instantiates a new User standard command.
     *
     * @param standardUser the standard user
     */
    public UserStandardCommand(StandardUser standardUser) {
        name = standardUser.getName();
        email = standardUser.getEmail();
        street = standardUser.getStreet();
        country = standardUser.getCountry();
        city = standardUser.getCity();
        isEmailVerified = standardUser.isEmailVerified();
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

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets is admin.
     *
     * @return the is admin
     */
    public boolean getIsAdmin() {
        return isAdmin;
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
     * Gets is email verified.
     *
     * @return the is email verified
     */
    public boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    /**
     * Sets is email verified.
     *
     * @param emailVerified the email verified
     */
    public void setIsEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
}
