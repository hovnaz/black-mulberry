package com.black.mulberry.core.service;

/**
 * Provides methods for sending emails.
 */
public interface MailService {

    /**
     * Sends an email to the specified email address.
     *
     * @param to      the email address of the recipient
     * @param subject the subject of the email
     * @param text    the content of the email
     */
    void sendEmail(String to, String subject, String text);
}
