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

    private final String HOST_ADDRESS = "89.77.244.32:80";

    @Override
    public void sendVerificationEmail(Long userId, String emailAddress, String verificationToken) {

        String emailContent = "https://" + HOST_ADDRESS + "/user/verify-email?userId=" + userId + "&verificationToken=" + verificationToken;
        sendEmail(emailAddress, "Email Verification", emailContent);


//        SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
//        crunchifyMsg.setFrom(COMPANY_ADDRESS);
//        crunchifyMsg.setTo(emailAddress);
//        crunchifyMsg.setSubject("Email Verification");
//        crunchifyMsg.setText("https://{host}/user/verify-email?userId=" + userId + "&verificationToken=" + verificationToken);
//        crunchifymail.send(crunchifyMsg);

    }

    @Override
    public void sendEmail(String emailAddress, String emailSubject, String emailContent) {

        // multithreading

        Runnable emailTask = () -> {
            SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
            crunchifyMsg.setFrom(COMPANY_ADDRESS);
            crunchifyMsg.setTo(emailAddress);
            crunchifyMsg.setSubject(emailSubject);
            crunchifyMsg.setText(emailContent);
            crunchifymail.send(crunchifyMsg);
        };

        new Thread(emailTask).start();
    }
}
