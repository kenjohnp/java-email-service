package com.kenjohn.email_service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

public class EmailServiceImpl implements EmailService {
	private Session session;
	private String from;

	
	public EmailServiceImpl(Properties config) throws MessagingException {
		String username;
		String password;

		if(config.getProperty("mail.smtp.auth") != null && config.getProperty("mail.smtp.auth").toString().equals("true")) {
			if(config.getProperty("username") == null ||
					config.getProperty("username") == null) return;

			username = config.getProperty("username");
			password = config.getProperty("password");
			session = Session.getDefaultInstance(config, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		}
		else
			session = Session.getDefaultInstance(config);

		if(config.getProperty("from") != null)
			this.from = config.getProperty("from");

	}


	@Override
	public void send(EmailMessage emailMessage) throws MessagingException {
		Transport.send(buildMessage(emailMessage));
	}

	private MimeMessage buildMessage(EmailMessage emailMessage) throws MessagingException {
		MimeMessage message = new MimeMessage(session);

		Address[] to = emailMessage.getTo().toArray(new Address[0]);
		Address[] cc = emailMessage.getCc().toArray(new Address[0]);
		Address[] bcc = emailMessage.getBcc().toArray(new Address[0]);

		message.setRecipients(MimeMessage.RecipientType.TO, to);
		message.setRecipients(MimeMessage.RecipientType.CC, cc);
		message.setRecipients(MimeMessage.RecipientType.BCC, bcc);

		message.setFrom(from);
		message.setContent(emailMessage.getBody(), "text/html; charset=utf-8");
		message.setSubject(emailMessage.getSubject());

		return message;
	}

}
