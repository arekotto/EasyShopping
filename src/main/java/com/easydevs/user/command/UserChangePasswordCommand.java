package com.easydevs.user.command;

/**
 * Created by arekotto on 29/04/2017.
 */
public class UserChangePasswordCommand {
    private String currentPassword;
    private String newPassword;
    private String newPasswordRetyped;

    private String errorMessage;

    /**
     * Gets current password.
     *
     * @return the current password
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * Sets current password.
     *
     * @param currentPassword the current password
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    /**
     * Gets new password.
     *
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets new password.
     *
     * @param newPassword the new password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Gets new password retyped.
     *
     * @return the new password retyped
     */
    public String getNewPasswordRetyped() {
        return newPasswordRetyped;
    }

    /**
     * Sets new password retyped.
     *
     * @param newPasswordRetyped the new password retyped
     */
    public void setNewPasswordRetyped(String newPasswordRetyped) {
        this.newPasswordRetyped = newPasswordRetyped;
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
