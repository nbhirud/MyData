package com.triveni.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
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
import javax.servlet.http.HttpSession;

import com.triveni.DAO.RegistrationDAO;
import com.triveni.beans.RegistrationBean;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecureRandom random = new SecureRandom();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// public void sendMail(String mailId,String outputFile) {
	public void sendMail(String mailId, String triveniId, String tempPassword) {
		final String username = "triveniisauncc@gmail.com";
		final String password = "2015Triveni";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("triveniisauncc@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailId));
			message.setSubject("Registration Confirmation");

			// MimeBodyPart messageBodyPart = new MimeBodyPart();
			message.setText("Welcome to Triveni - UNC Charlotte Indian Students Association. \n "
					+ "To confirm your registration please enter the Following Login Credenials by entering the following Url in your browser \n http://www.triveniuncc.org/index.jsp#login \n"
					+ "Triveni Id : " + triveniId + "\nPassword : " + tempPassword);
			// Multipart multipart = new MimeMultipart();
			// multipart.addBodyPart(messageBodyPart);
			//
			// messageBodyPart = new MimeBodyPart();
			/*
			 * DataSource source = new FileDataSource(outputFile);
			 * messageBodyPart.setDataHandler( new DataHandler(source));
			 * messageBodyPart.setFileName("MyFile.pdf");
			 */
			// multipart.addBodyPart(messageBodyPart);
			//
			// // Put parts in message
			// message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email") + "@uncc.edu";
		String gender = request.getParameter("gender");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String extension = request.getParameter("extension");
		String phone = request.getParameter("phone");
		String phnNumber = "+" + extension + phone;
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String term = request.getParameter("term");
		String year = request.getParameter("year");
		String permanentContact = "+" + request.getParameter("permanentContact");
		RegistrationBean registrationBean = new RegistrationBean();
		registrationBean.setFirstname(firstname);
		registrationBean.setLastname(lastname);
		registrationBean.setEmail(email);
		registrationBean.setGender(gender);
		registrationBean.setPermanentContact(permanentContact);
		registrationBean.setAddress1(address1);
		registrationBean.setAddress2(address2);
		registrationBean.setPhnNumber(phnNumber);
		registrationBean.setCity(city);
		registrationBean.setZip(zip);
		registrationBean.setState(state);
		registrationBean.setCountry(country);
		registrationBean.setTerm(term);
		registrationBean.setYear(year);
		String tempPassword = new BigInteger(26, random).toString(32);
		registrationBean.setTempPassowrd(tempPassword);
		HttpSession session1 = request.getSession();
		if (email.contains("@uncc.edu") && !email.contains(" ")) {
			RegistrationDAO registrationDAO = new RegistrationDAO();
			int triveniId = registrationDAO.registerUSer(registrationBean);
			if (triveniId != 0) {
				/*
				 * String to = email;// change accordingly String from =
				 * "noreply@triveni.com";// change accordingly String host =
				 * "localhost";// or IP address
				 * 
				 * // Get the session object Properties properties =
				 * System.getProperties();
				 * properties.setProperty("mail.smtp.host", host); Session
				 * session = Session.getDefaultInstance(properties);
				 */

				// compose the message
				/*
				 * try { MimeMessage message = new MimeMessage(session);
				 * message.setFrom(new InternetAddress(from));
				 * message.addRecipient(Message.RecipientType.TO, new
				 * InternetAddress(to));
				 * message.setSubject("Registration Confirmation");
				 * 
				 * message.setText(
				 * "Welcome to Triveni - UNC Charolotte Indian Students Organization \n"
				 * +
				 * "To confirm your registration please enter the Following Login Credenials \n"
				 * + "Triveni Id : "+triveniId + "\nPassword : " +tempPassword);
				 * Transport.send(message);
				 */
				sendMail(email, triveniId + "", tempPassword);

				session1.setAttribute("status", "Thanks for registering in Triveni Website. An email has been sent to " + email
						+ ". Please check your email to login.");
				response.sendRedirect("index.jsp#login");
				/*
				 * } catch (MessagingException mex) { mex.printStackTrace(); }
				 */
			} else {
				session1.setAttribute("status",
						"Sorry!!! There is an error processing your request to register into this website. Please try again after sometime.");
				response.sendRedirect("index.jsp#login");
			}
		} else {
			session1.setAttribute("status",
					"Sorry!!! There is an error processing your email. Please check the email given and try again");
			response.sendRedirect("index.jsp#login");
		}
	}

}
