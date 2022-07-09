package com.kenjohn.email_service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailServiceImplTest {

    @Test
    void testSend() throws MessagingException, IOException {
        GreenMail greenMail = new GreenMail(new ServerSetup(3025,"localhost",ServerSetup.PROTOCOL_SMTP));
        greenMail.start();

        var message = new EmailMessage();
        message.setSubject("subject");
        message.appendBody("sample");
        message.addRecipient(RecipientTypes.TO, "kenjohnp@test.com");

        Properties props = new Properties();
        props.load(new FileInputStream("src/test/resources/mail.properties"));

        EmailService emailService = new EmailServiceImpl(props);

        emailService.send(message);

        final MimeMessage[] receivedMessages = greenMail.getReceivedMessages();

        assertEquals(1, receivedMessages.length);
        assertEquals("subject", receivedMessages[0].getSubject());

        greenMail.stop();
    }

}
