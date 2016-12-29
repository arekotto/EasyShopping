package com.easydevs.user.command;

/**
 * Created by arekotto on 24/12/2016.
 */
public class UserLoginCommand {
    private String email;
    private String password;

    private boolean isLoginFailed;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setIsLoginFailed(boolean loginFailed) {
        isLoginFailed = loginFailed;
    }
}
