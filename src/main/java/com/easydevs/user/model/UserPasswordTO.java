package com.easydevs.user.model;

/**
 * Created by arekotto on 27/12/2016.
 */
public class UserPasswordTO {
    private Long userId;
    private String passwordHash;

    public UserPasswordTO() {
    }

    public UserPasswordTO(Long userId, String passwordHash) {
        this.userId = userId;
        this.passwordHash = passwordHash;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
