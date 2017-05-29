package com.easydevs.user.command;

/**
 * Created by arekotto on 12/05/2017.
 */
public class UserChangeEmailCommand {
    private String newEmail;
    private String errorMessage;

    /**
     * Gets new email.
     *
     * @return the new email
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * Sets new email.
     *
     * @param newEmail the new email
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
