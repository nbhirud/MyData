package com.triveni.sendemail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

	public static void main(String[] args) {
		String to = "ppuli@uncc.edu";// change accordingly
		String from = "ITHELPDESK@triveni.com";// change accordingly
		String host = "localhost";// or IP address

		// Get the session object
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		// compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Welcome to Triveni");
			message.setText("Triveni - UNC Charolotte Indian Students Organization  ");

			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
