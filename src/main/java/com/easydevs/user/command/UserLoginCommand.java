package com.easydevs.user.command;

/**
 * Created by arekotto on 24/12/2016.
 */
public class UserLoginCommand {
    private String login;
    private String password;

    private boolean isLoginFailed;

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

    public boolean getIsLoginFailed() {
        return isLoginFailed;
    }

    public void setLoginFailed(boolean loginFailed) {
        isLoginFailed = loginFailed;
    }
}
