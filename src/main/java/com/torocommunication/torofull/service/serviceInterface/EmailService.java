package com.torocommunication.torofull.service.serviceInterface;

import javax.mail.MessagingException;

public interface EmailService {
    void sendNewEmail(String username, String email, String subject) throws MessagingException;

    String buildEmail(String name, String link);
}
