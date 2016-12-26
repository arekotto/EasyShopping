package com.easydevs.user.command;

/**
 * Created by arekotto on 16/12/2016.
 */
public class UserRegistrationCommand {
    private String login;
    private String password;
    private String name;

    private boolean isLoginUnavailable;
    private boolean isPasswordFormatCorrect;

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

    public boolean isLoginUnavailable() {
        return isLoginUnavailable;
    }

    public void setLoginUnavailable(boolean loginUnavailable) {
        isLoginUnavailable = loginUnavailable;
    }

    public boolean isPasswordFormatCorrect() {
        return isPasswordFormatCorrect;
    }

    public void setPasswordFormatCorrect(boolean passwordFormatCorrect) {
        isPasswordFormatCorrect = passwordFormatCorrect;
    }
}
