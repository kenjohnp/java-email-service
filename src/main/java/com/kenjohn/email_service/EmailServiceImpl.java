package com.kenjohn.email_service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailServiceImpl implements EmailService {
	private MimeMessage message;
	private Session session;
	private Multipart body = new MimeMultipart();
	
	public EmailServiceImpl(Properties config) {
		String username = config.getProperty("username");
		String password = config.getProperty("password");
		
		if(config.getProperty("mail.smtp.auth").toString().equals("true"))
			session = Session.getDefaultInstance(config, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		else
			session = Session.getDefaultInstance(config);
		
		message = new MimeMessage(session);
	}

	@Override
	public void send() throws MessagingException {
		message.setContent(body, "text/html; charset=utf-8");
		Transport.send(message);	
		System.out.println("Message sent!");
	}

	@Override
	public void addRecipientTo(String email) throws AddressException, MessagingException {
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	}

	@Override
	public void addRecipientCc(String email) throws AddressException, MessagingException {
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
	}

	@Override
	public void addRecipientBcc(String email) throws AddressException, MessagingException {
		message.addRecipient(Message.RecipientType.BCC, new InternetAddress(email));
	}

	@Override
	public void addRecipientsTo(String[] emails) throws AddressException, MessagingException {	
		message.addRecipients(Message.RecipientType.TO, getRecipientsFromArray(emails));
	}

	@Override
	public void addRecipientsCc(String[] emails) throws AddressException, MessagingException {
		message.addRecipients(Message.RecipientType.CC, getRecipientsFromArray(emails));
	}

	@Override
	public void addRecipientsBcc(String[] emails) throws AddressException, MessagingException {
		message.addRecipients(Message.RecipientType.BCC, getRecipientsFromArray(emails));
	}
	
	
	private InternetAddress[] getRecipientsFromArray(String[] emails) throws AddressException {
		InternetAddress[] recipients = new InternetAddress[emails.length];
		
		for(int i = 0; i < emails.length; i++) {
			recipients[i] = new InternetAddress(emails[i]);
		}
		
		return recipients;
	}

	@Override
	public void setSubject(String subject) throws MessagingException {
		message.setSubject(subject);
	}
	
	@Override
	public void appendBody(String string) throws MessagingException {
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(string, "text/html; charset=utf-8");
		body.addBodyPart(messageBodyPart);	
	}

	@Override
	public void setFrom(String email) throws MessagingException {
		message.setFrom(email);
	}

	@Override
	public void attachFile(File file) throws IOException, MessagingException {
		MimeBodyPart attachment = new MimeBodyPart();
		attachment.attachFile(file);
		body.addBodyPart(attachment);
	}
	
}
