package com.easydevs.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Arek on 07.01.2017.
 */
public interface RequestVerificationService {
    boolean verifyRequest(HttpServletRequest request);
}

