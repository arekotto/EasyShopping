package com.easydevs.user.command;

/**
 * Created by arekotto on 24/12/2016.
 */
public class UserLoginCommand {
    private String email;
    private String password;

    private boolean isLoginFailed;

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
     * Gets is login failed.
     *
     * @return the is login failed
     */
    public boolean getIsLoginFailed() {
        return isLoginFailed;
    }

    /**
     * Sets is login failed.
     *
     * @param loginFailed the login failed
     */
    public void setIsLoginFailed(boolean loginFailed) {
        isLoginFailed = loginFailed;
    }
}
