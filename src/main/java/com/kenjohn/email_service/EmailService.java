package com.kenjohn.email_service;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {

	public void send() throws MessagingException;
	
	public void setFrom(String email) throws MessagingException;
	
	public void addRecipientTo(String email) throws AddressException, MessagingException;
	public void addRecipientCc(String email) throws AddressException, MessagingException;
	public void addRecipientBcc(String email) throws AddressException, MessagingException;
	public void addRecipientsTo(String[] emails) throws AddressException, MessagingException;
	public void addRecipientsCc(String[] emails) throws AddressException, MessagingException;
	public void addRecipientsBcc(String[] emails) throws AddressException, MessagingException;
	
	public void setSubject(String subject) throws MessagingException;
	
	public void appendBody(String body) throws MessagingException;
	
	public void setBody(String body) throws MessagingException;

	public void attachFile(File file) throws IOException, MessagingException;
	
	
}
