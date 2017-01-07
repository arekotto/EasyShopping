package com.easydevs.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Arek on 07.01.2017.
 */
@Service
public class RequestVerificationServiceImpl implements RequestVerificationService {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean verifyRequest(HttpServletRequest request) {
        String token = "";
        String userId = "";
        if (request.getCookies() == null) {
            return false;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            } else if (cookie.getName().equals("id")) {
                userId = cookie.getValue();
            }
        }
        return !(token.isEmpty() || userId.isEmpty()) && authenticationService.isTokenValid(Long.parseLong(userId), token);
    }
}
