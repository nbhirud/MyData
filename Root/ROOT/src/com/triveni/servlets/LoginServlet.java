package com.triveni.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.triveni.DAO.LoginDAO;
import com.triveni.beans.BillBean;
import com.triveni.beans.EventBean;
import com.triveni.beans.ItenaryBean;
import com.triveni.beans.PaymentBean;
import com.triveni.beans.PhotoBean;
import com.triveni.beans.RegistrationBean;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String triveniId = request.getParameter("triveniId");
		HttpSession session = request.getSession();
		session.setAttribute("userId", triveniId);
		String password = request.getParameter("password");
		LoginDAO loginDAO = new LoginDAO();
		triveniId = loginDAO.checkLogin(triveniId, password);
		if (triveniId != null) {
			session.setAttribute("triveniId", triveniId);
			RegistrationBean registrationBean = loginDAO.fetchUserDetails(triveniId);
			session.setAttribute("userDetails", registrationBean);
			loginDAO.fetchPayment(triveniId);
			ItenaryBean itenaryBean = loginDAO.checkItenaryDetails(triveniId);
			session.setAttribute("itenaryDetails", itenaryBean);
			ArrayList<EventBean> eventBeans = loginDAO.fetchEvents();
			session.setAttribute("eventDetails", eventBeans);
			ArrayList<PaymentBean> paymentBeans = loginDAO.fetchPayment(triveniId);
			session.setAttribute("paymentDetails", paymentBeans);
			if(registrationBean.getUserRole().equalsIgnoreCase("treasurer")==true){
				ArrayList<BillBean> billBeans = loginDAO.fetchBillsData();
				session.setAttribute("billDetails", billBeans);
			}
			else if(registrationBean.getUserRole().equalsIgnoreCase("Event organizer")==true){
				ArrayList<PhotoBean> photoBeans = loginDAO.fetchPhotos();
				session.setAttribute("photoDetails", photoBeans);
			}
			response.sendRedirect("userprofile/index.jsp");
		} else {
			response.sendRedirect("index.jsp#login?status=0");
		}
	}

}
