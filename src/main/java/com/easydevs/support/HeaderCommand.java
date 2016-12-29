package com.easydevs.support;

/**
 * Created by arekotto on 16/12/2016.
 */
public class HeaderCommand {
    private boolean isUserLoggedIn = false;
    private String userName;
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean getIsLoggedIn() {
        return isUserLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isUserLoggedIn = isLoggedIn;
    }
}
