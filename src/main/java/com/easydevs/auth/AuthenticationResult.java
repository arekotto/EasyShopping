package com.easydevs.auth;

/**
 * Created by arekotto on 15/12/2016.
 */
public class AuthenticationResult {
    private Boolean isSuccessful;
    private String token;

    /**
     * Instantiates a new Authentication result.
     */
    public AuthenticationResult() {
    }

    /**
     * Instantiates a new Authentication result.
     *
     * @param isSuccessful the is successful
     * @param token        the token
     */
    public AuthenticationResult(Boolean isSuccessful, String token) {
        this.isSuccessful = isSuccessful;
        this.token = token;
    }

    /**
     * Gets successful.
     *
     * @return the successful
     */
    public Boolean getSuccessful() {
        return isSuccessful;
    }

    /**
     * Sets successful.
     *
     * @param successful the successful
     */
    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }
}
