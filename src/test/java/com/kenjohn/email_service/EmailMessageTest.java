package com.kenjohn.email_service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EmailMessageTest {
	
	@Nested
	@DisplayName("Adding Recipients")
	class AddRecipients{
		@Test
		void testAddRecipientTo() throws AddressException {
			EmailMessage email = new EmailMessage();
			email.addRecipient(RecipientTypes.TO, "kenjohnp@gmail.com");
			
			assertEquals(1, email.getTo().size());
			assertTrue(email.getTo().contains(new InternetAddress("kenjohnp@gmail.com")));
		}
		
		@Test
		void testAddRecipientCc() throws AddressException {
			EmailMessage email = new EmailMessage();
			email.addRecipient(RecipientTypes.CC, "kenjohnp@gmail.com");
			
			assertEquals(1, email.getCc().size());
			assertTrue(email.getCc().contains(new InternetAddress("kenjohnp@gmail.com")));
		}
		
		@Test
		void testAddRecipientBcc() throws AddressException {
			EmailMessage email = new EmailMessage();
			email.addRecipient(RecipientTypes.BCC, "kenjohnp@gmail.com");
			
			assertEquals(1, email.getBcc().size());
			assertTrue(email.getBcc().contains(new InternetAddress("kenjohnp@gmail.com")));
		}
		
		@Test
		void testAddRecipientWithInvalidEmail() {
			var email = new EmailMessage();
			assertThrows(AddressException.class, () -> email.addRecipient(RecipientTypes.TO, "kenjohn"));
		}
		
		@Test
		void testAddRecipientWithInvalidType() {
			var email = new EmailMessage();
			assertThrows(IllegalArgumentException.class, () -> email.addRecipient(null, "kenjohn"));
		}
		
		@Test
		void testAddRecipientsWithInvalidEmail() {
			var email = new EmailMessage();
			List<String> emails = List.of("kenjohnp@gmail.com", "kenjohnp");
			
			assertThrows(AddressException.class, () -> email.addRecipients(RecipientTypes.TO, emails));
		}
		
		@Test
		void testAddRecipientsWithInvalidType() {
			var email = new EmailMessage();
			List<String> emails = List.of("kenjohnp@gmail.com", "kenjohnp2@gmail.com");
			
			assertThrows(IllegalArgumentException.class, () -> email.addRecipients(null, emails));
		}
		
		
		@Test
		void testAddRecipientsTo() throws AddressException {
			var message = new EmailMessage();
			List<String> emails = List.of("kenjohnp@gmail.com", "kenjohnp2@gmail.com");
			
			message.addRecipients(RecipientTypes.TO, emails);
			
			assertEquals(2, message.getTo().size());	
			assertTrue(message.getTo().contains(new InternetAddress("kenjohnp2@gmail.com")));	
		}
		
		@Test
		void testAddRecipientsCc() throws AddressException {
			var message = new EmailMessage();
			List<String> emails = List.of("kenjohnp@gmail.com", "kenjohnp2@gmail.com");
			
			message.addRecipients(RecipientTypes.CC, emails);
			
			assertEquals(2, message.getCc().size());	
			assertTrue(message.getCc().contains(new InternetAddress("kenjohnp2@gmail.com")));	
		}

		@Test
		void testAddRecipientsBcc() throws AddressException {
			var message = new EmailMessage();
			List<String> emails = List.of("kenjohnp@gmail.com", "kenjohnp2@gmail.com");
			
			message.addRecipients(RecipientTypes.BCC, emails);
			
			assertEquals(2, message.getBcc().size());	
			assertTrue(message.getBcc().contains(new InternetAddress("kenjohnp2@gmail.com")));	
		}
		
	}
	@Test
	void testAppendBody() throws MessagingException, IOException {
		var message = new EmailMessage();
		String bodyPart1 = "Part 1";
		String bodyPart2 = "Part 2";

		message.appendBody(bodyPart1);
		message.appendBody(bodyPart2);

		assertTrue(message.getBody().getBodyPart(0).getContent().equals(bodyPart1));
		assertTrue(message.getBody().getBodyPart(1).getContent().equals(bodyPart2));
	}

	@Test
	void testSetBody() throws MessagingException, IOException {
		var message = new EmailMessage();
		String bodyPart1 = "Part 1";
		String bodyPart2 = "Part 2";

		message.appendBody(bodyPart1);
		message.appendBody(bodyPart2);

		message.setBody("Whole Part");

		assertEquals(1, message.getBody().getCount());
		assertTrue(message.getBody().getBodyPart(0).getContent().equals("Whole Part"));
	}

	@Test
	void testAttachFile() throws MessagingException, IOException {
		var message = new EmailMessage();
		message.attachFile(new File("sample.txt"));

		assertEquals(1, message.getBody().getCount());
	}

	
}
