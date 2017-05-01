package com.easydevs.user.command;

/**
 * Created by arekotto on 29/04/2017.
 */
public class UserChangePasswordCommand {
    private String currentPassword;
    private String newPassword;
    private String newPasswordRetyped;

    private String errorMessage;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRetyped() {
        return newPasswordRetyped;
    }

    public void setNewPasswordRetyped(String newPasswordRetyped) {
        this.newPasswordRetyped = newPasswordRetyped;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
