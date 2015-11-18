package com.triveni.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.triveni.DAO.ChangeUserDAO;
@WebServlet("/ChangeUserRoleServlet")
public class ChangeUserRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String triveniId = request.getParameter("triveniId");
		String userRole = request.getParameter("userRole");
		ChangeUserDAO changeUserDAO = new ChangeUserDAO();
		int result = changeUserDAO.changeUser(triveniId,userRole);
		if(result != 0){
			response.sendRedirect("userprofile/defineUsers.jsp?status=successful");
		}else{
			response.sendRedirect("userprofile/defineUsers.jsp?status=failure");
		}
	}

}
