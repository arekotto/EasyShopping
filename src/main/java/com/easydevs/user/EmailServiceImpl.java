package com.easydevs.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by arekotto on 28/01/2017.
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private MailSender crunchifymail;

    private final String COMPANY_ADDRESS = "easyydeve@gmail.com";


    @Override
    public void sendVerificationEmail(Long userId, String userEmail, String verificationToken) {

        SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
        crunchifyMsg.setFrom(COMPANY_ADDRESS);
        crunchifyMsg.setTo(userEmail);
        crunchifyMsg.setSubject("Email Verification");
        crunchifyMsg.setText("https://{host}/user/verify-email?userId=" + userId + "&verificationToken=" + verificationToken);
        crunchifymail.send(crunchifyMsg);

    }
}
