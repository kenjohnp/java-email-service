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
    	
    	EmailService emailService = new EmailServiceImpl(props);
    	
    	try {
    		emailService.setFrom("email@gmail.com");
			emailService.addRecipientTo("email@gmail.com");
			emailService.setSubject("Sample Email Subject");
			emailService.appendBody("<html><b>Sample Body using HTML</b></html>");
			emailService.appendBody("Plain Text body appended");
			//emailService.attachFile(new File("E:\\folder\\file.txt"));
			emailService.send();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
        
    }
}
