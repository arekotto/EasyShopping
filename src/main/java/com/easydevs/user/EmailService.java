package com.easydevs.user;

/**
 * Created by arekotto on 28/01/2017.
 */
public interface EmailService {
    void sendVerificationEmail(Long userId, String email, String verificationToken);
}
