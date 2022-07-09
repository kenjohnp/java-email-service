package com.kenjohn.email_service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;

public class Main 
{
    public static void main( String[] args )
    {
    	Properties props = new Properties();
    	
    	try {
			props.load(new FileInputStream("src/main/resources/mail.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
    	EmailMessage email = new EmailMessage();
    	
    		try {
				EmailService emailService = new EmailServiceImpl(props);
				email.addRecipient(RecipientTypes.TO, "kenjohnp@gmail.com");
				email.setSubject("Sample Email Subject");
				email.appendBody("<html><b>Sample Body using HTML</b></html>");
				email.appendBody("Plain Text body appended");
				email.attachFile(new File("E:\\DCIM\\Screenshots\\12.jpg"));
				emailService.send(email);
			} catch (MessagingException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


	
    }
}
