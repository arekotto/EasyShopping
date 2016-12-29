package com.easydevs.support;

/**
 * Created by arekotto on 16/12/2016.
 */
public class HeaderCommand {
    private boolean isUserLoggedIn = false;
    private String userName;
    private String userLogin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public boolean getIsLoggedIn() {
        return isUserLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isUserLoggedIn = isLoggedIn;
    }
}
