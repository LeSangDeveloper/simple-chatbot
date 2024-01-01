/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simple_chatbot.service.impl;

import com.mycompany.simple_chatbot.config.ConfigManager;
import com.mycompany.simple_chatbot.service.EmailService;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lesan
 */
public class EmailServiceImpl implements EmailService {

    private final static EmailServiceImpl INSTANCE = new EmailServiceImpl();
    private final static ConfigManager config = ConfigManager.getInstance();
    
    private EmailServiceImpl() {
    }
    
    public static EmailServiceImpl getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void sendEmail(String mailAddress, String resetCode) {
        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
                // Create a Session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(config.getEmailUsername(), config.getEmailPassword());
            }
        });
     
        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set sender and recipient addresses
            message.setFrom(new InternetAddress(config.getEmailUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailAddress));

            // Set email subject and body
            message.setSubject("Reset Password from Simple Chatbot");
            message.setText(resetCode);

            // Send the email
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
