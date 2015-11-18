package com.triveni.servlets;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.triveni.DAO.LoginDAO;
import com.triveni.DAO.ProfileDAO;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePasswordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String triveniId = (String) session.getAttribute("triveniId");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		LoginDAO loginDAO = new LoginDAO();
		String result = loginDAO.checkLogin(triveniId, currentPassword);
		PrintWriter out = response.getWriter();
		if (result != null) {
			ProfileDAO profileDAO = new ProfileDAO();
			profileDAO.changePassword(triveniId, currentPassword, newPassword);
			out.print("Updated Successfully");
		}else{
			out.print("Current Password Incorrect");
		}

	}

}
