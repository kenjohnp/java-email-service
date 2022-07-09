package com.kenjohn.email_service;

import javax.mail.MessagingException;

public interface EmailService {

	public void send(EmailMessage email) throws MessagingException;	
	
}
