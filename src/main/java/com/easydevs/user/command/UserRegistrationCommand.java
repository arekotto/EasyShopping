package com.easydevs.user.command;

/**
 * Created by arekotto on 16/12/2016.
 */
public class UserRegistrationCommand {
    private String login;
    private String password;
    private String name;

    private String email;

    private boolean isLoginUnavailable = false;
    private boolean isPasswordFormatIncorrect = false;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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
        return isLoginUnavailable;
    }

    public void setIsLoginUnavailable(boolean loginUnavailable) {
        isLoginUnavailable = loginUnavailable;
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
}
