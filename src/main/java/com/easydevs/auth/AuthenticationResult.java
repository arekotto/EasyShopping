package com.easydevs.auth;

/**
 * Created by arekotto on 15/12/2016.
 */
public class AuthenticationResult {
    private Boolean isSuccessful;
    private String token;

    public AuthenticationResult() {
    }

    public AuthenticationResult(Boolean isSuccessful, String token) {
        this.isSuccessful = isSuccessful;
        this.token = token;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
