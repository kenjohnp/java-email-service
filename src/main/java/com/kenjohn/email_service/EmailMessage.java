package com.kenjohn.email_service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailMessage {

	private List<InternetAddress> to = new ArrayList<>();
	private List<InternetAddress> cc = new ArrayList<>();
	private List<InternetAddress> bcc = new ArrayList<>();

	private String subject = "";

	private Multipart body = new MimeMultipart();

	public void addRecipient(RecipientTypes recipientType, String email) throws AddressException {
		if(recipientType == null)
			throw new IllegalArgumentException();

		switch(recipientType) {
			case TO:
				to.add(new InternetAddress(email, true));
				break;
			case CC:
				cc.add(new InternetAddress(email, true));
				break;
			case BCC:
				bcc.add(new InternetAddress(email, true));
				break;
		}
	}

	public void addRecipients(RecipientTypes recipientType, List<String> emails) throws AddressException {
		if(recipientType == null)
			throw new IllegalArgumentException();

		switch(recipientType) {
			case TO:
				to.addAll(listOfStringToInternetAddress(emails));
				break;
			case CC:
				cc.addAll(listOfStringToInternetAddress(emails));
				break;
			case BCC:
				bcc.addAll(listOfStringToInternetAddress(emails));
				break;
		}

	}

	private List<InternetAddress> listOfStringToInternetAddress(List<String> emailList) throws AddressException {
		List<InternetAddress> internetAddressList = new ArrayList<InternetAddress>();

		for(String email : emailList) {
			internetAddressList.add(new InternetAddress(email, true));
		}

		return internetAddressList;
	}

	public void appendBody(String string) throws MessagingException {
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(string, "text/html; charset=utf-8");
		body.addBodyPart(messageBodyPart);

	}

	public void attachFile(File file) throws IOException, MessagingException {
		MimeBodyPart attachment = new MimeBodyPart();
		attachment.attachFile(file);
		body.addBodyPart(attachment);
	}

	public void setSubject(String subject) throws MessagingException {
		this.subject = subject;
	}

	public void setBody(String body) throws MessagingException {
		this.body = new MimeMultipart();
		appendBody(body);
	}

	public Multipart getBody() {
		return body;
	}

	public List<InternetAddress> getTo() {
		return to;
	}

	public List<InternetAddress> getCc() {
		return cc;
	}

	public List<InternetAddress> getBcc() {
		return bcc;
	}

	public String getSubject() {
		return subject;
	}


}
