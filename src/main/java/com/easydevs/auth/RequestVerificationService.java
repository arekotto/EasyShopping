package com.easydevs.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Arek on 07.01.2017.
 */
public interface RequestVerificationService {
    /**
     * Verify request boolean.
     *
     * @param request the request
     * @return the boolean
     */
    boolean verifyRequest(HttpServletRequest request);
}

