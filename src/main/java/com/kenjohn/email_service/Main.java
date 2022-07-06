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
    		emailService.setFrom("kenjohnp123@gmail.com");
			emailService.addRecipientTo("kenjohnp@gmail.com");
			emailService.setSubject("Murahan 2022");
			emailService.appendBody("<html><b>tnginanyo</b></html>");
			emailService.attachFile(new File("E:\\gp-price-checker\\jsconfig.json"));
			emailService.send();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
        
    }
}
