package com.triveni.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.triveni.DAO.LoginDAO;
import com.triveni.DAO.ProfileDAO;
import com.triveni.beans.ItenaryBean;
import com.triveni.beans.RegistrationBean;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String phnNumber = request.getParameter("phone");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String permanentContact = request.getParameter("permanentContact");
		RegistrationBean registrationBean = new RegistrationBean();
		registrationBean.setFirstname(firstname);
		registrationBean.setLastname(lastname);
		registrationBean.setEmail(email);
		registrationBean.setGender(gender);
		registrationBean.setAddress1(address1);
		registrationBean.setAddress2(address2);
		registrationBean.setPhnNumber(phnNumber);
		registrationBean.setCity(city);
		registrationBean.setZip(zip);
		registrationBean.setState(state);
		registrationBean.setCountry(country);
		registrationBean.setPermanentContact(permanentContact);
		ProfileDAO profileDAO = new ProfileDAO();
		HttpSession session = request.getSession();
		String triveniId = (String) session.getAttribute("triveniId");
		int result = profileDAO.updateProfile(triveniId,registrationBean);
		if(result != 0){
			LoginDAO loginDAO = new LoginDAO();
			HttpSession session2 = request.getSession();
			session2.setAttribute("userDetails",loginDAO.fetchUserDetails(triveniId));
			ItenaryBean itenaryBean = loginDAO.checkItenaryDetails(triveniId);
			session.setAttribute("itenaryDetails", itenaryBean);
			response.sendRedirect("userprofile/profile.jsp");
		}else{
			response.sendRedirect("userprofile/index.jsp");
		}
	}

}
