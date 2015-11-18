package com.triveni.servlets;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.triveni.DAO.ChangeUserDAO;
import com.triveni.DAO.LoginDAO;
import com.triveni.beans.RegistrationBean;

@WebServlet("/MakePaymentServlet")
public class MakePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String triveniId = request.getParameter("triveniId");
		String paymentDate = request.getParameter("paidOn");
		String paymentAmount = request.getParameter("paidAmount");
		ChangeUserDAO changeUserDAO = new ChangeUserDAO();
		int result = changeUserDAO.makePayment(triveniId, paymentDate, paymentAmount);
		LoginDAO loginDAO = new LoginDAO();
		if (result != 0) {
			RegistrationBean registrationBean = loginDAO.fetchUserDetails(triveniId);
			String email = registrationBean.getEmail();
			/*String to = email;// change accordingly
			String from = "noreply@triveni.com";// change accordingly
			String host = "localhost";// or IP address

			// Get the session object
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);
			Session session = Session.getDefaultInstance(properties);*/
			final String username = "triveniisauncc@gmail.com";
		    final String password = "2015Triveni";

		    Properties props = new Properties();
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.socketFactory.class",
		            "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.port", "465");

		    Session session = Session.getDefaultInstance(props,
		        new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username,password);
		            }
		        });

			// compose the message
			try {
				MimeMessage message = new MimeMessage(session);
				/*message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));*/
				message.setFrom(new InternetAddress("triveniisauncc@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				message.setSubject("Payment Confirmation");

				message.setText("Hello "+registrationBean.getFirstname()+" \nWelcome to Triveni - UNC Charolotte Indian Students Association \n"
						+ "Your payment has been successfully recorded \n"
						+"Amount paid: "+paymentAmount
						+"\nPaid on:"+ paymentDate
						+"\nThank you very much for making the payment towards Triveni.");
				Transport.send(message);
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
			response.sendRedirect("userprofile/triveniPayments.jsp?status=Successful");
		} else {
			response.sendRedirect("userprofile/triveniPayments.jsp?status=Failure");
		}
	}

}
