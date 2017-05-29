package com.easydevs.user;

/**
 * Created by arekotto on 28/01/2017.
 */
public interface EmailService {
    /**
     * Send verification email.
     *
     * @param userId            the user id
     * @param emailAddress      the email address
     * @param verificationToken the verification token
     */
    void sendVerificationEmail(Long userId, String emailAddress, String verificationToken);

    /**
     * Send email.
     *
     * @param emailAddress the email address
     * @param emailSubject the email subject
     * @param emailContent the email content
     */
    void sendEmail(String emailAddress,  String emailSubject, String emailContent);
}
