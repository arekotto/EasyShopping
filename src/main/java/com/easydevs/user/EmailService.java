package com.easydevs.user;

/**
 * Created by arekotto on 28/01/2017.
 */
public interface EmailService {
    void sendVerificationEmail(Long userId, String emailAddress, String verificationToken);

    void sendEmail(String emailAddress,  String emailSubject, String emailContent);
}
