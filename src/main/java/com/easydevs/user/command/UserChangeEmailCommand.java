package com.easydevs.user.command;

/**
 * Created by arekotto on 12/05/2017.
 */
public class UserChangeEmailCommand {
    private String newEmail;
    private String errorMessage;

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
