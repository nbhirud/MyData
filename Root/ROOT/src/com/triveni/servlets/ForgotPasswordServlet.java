package com.triveni.servlets;

import java.io.IOException;
import java.math.BigInteger;

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

import java.security.SecureRandom;
import java.util.Properties;

import com.triveni.DAO.ProfileDAO;

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecureRandom random = new SecureRandom();

	public ForgotPasswordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session1 = request.getSession();
		String email = null;
		System.out.println(request.getParameter("email"));
		if (request.getParameter("email") != null) {
			email = request.getParameter("email") + "@uncc.edu";
			System.out.println(email);
			String tempPassword = new BigInteger(26, random).toString(32);
			ProfileDAO profileDAO = new ProfileDAO();
			int result = profileDAO.forgotPassword(email, tempPassword);
			if (result != 0) {
				String triveniId = profileDAO.fetchId(email);
				if (triveniId != null) {

					/*
					 * String to = email;// change accordingly String from =
					 * "resetpssword@triveni.com";// change // accordingly
					 * String host = "localhost";// or IP address
					 */
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
					// Get the session object
					/*
					 * Properties properties = System.getProperties();
					 * properties.setProperty("mail.smtp.host", host); Session
					 * session = Session.getDefaultInstance(properties);
					 */
					// compose the message
					try {
						MimeMessage message = new MimeMessage(session);
						/*
						 * message.setFrom(new InternetAddress(from));
						 * message.addRecipient(Message.RecipientType.TO, new
						 * InternetAddress(to));
						 */
						message.setFrom(new InternetAddress("triveniisauncc@gmail.com"));
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
						message.setSubject("Password Reset");
						message.setText("Welcome to Triveni - UNC Charlotte Indian Students Association. \n"
								+ "To reset your password please enter the Following Login Credenials \n" + "\nUsername : " + triveniId + "\nPassword : "
								+ tempPassword+" \n by entering following url in your browser\nhttp://www.triveniuncc.org/index.jsp#login");
						Transport.send(message);
						session1.setAttribute("status", " An email has been sent to " + email + ". Please check your email to login.");
						response.sendRedirect("index.jsp#login");
					} catch (MessagingException mex) {
						mex.printStackTrace();
					}
				}
			} else {
				session1.setAttribute("status", " We are unable to find your email. Please enter corrrect email to request for a password");
				response.sendRedirect("index.jsp#login");
			}
		}
	}

}
