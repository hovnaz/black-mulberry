package com.black.mulberry.core.service;

/**
 * уsing the method sendEmail() of this interface,
 * we send an email to the user that you are registered.
 */
public interface MailService {

    void sendEmail(String to, String subject, String text);
}
