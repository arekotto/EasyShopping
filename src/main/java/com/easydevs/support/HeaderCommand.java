package com.easydevs.support;

/**
 * Created by arekotto on 16/12/2016.
 */
public class HeaderCommand {
    private boolean isUserLoggedIn = false;
    private String userName;
    private String userEmail;
    private boolean isAdmin;

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user email.
     *
     * @return the user email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets user email.
     *
     * @param userEmail the user email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Gets is logged in.
     *
     * @return the is logged in
     */
    public boolean getIsLoggedIn() {
        return isUserLoggedIn;
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
     * Sets is admin.
     *
     * @param isAdmin the is admin
     */
    public void setIsAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }

    /**
     * Sets is logged in.
     *
     * @param isLoggedIn the is logged in
     */
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isUserLoggedIn = isLoggedIn;
    }
}
