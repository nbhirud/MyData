package com.triveni.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.triveni.DAO.ChangeUserDAO;
import com.triveni.DAO.LoginDAO;
import com.triveni.beans.EventBean;
@WebServlet("/DeleteEventsServlet")
public class DeleteEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteEventsServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String eventId = request.getParameter("id");
		PrintWriter out =  response.getWriter();
		ChangeUserDAO changeUserDAO = new ChangeUserDAO();
		int result = changeUserDAO.deleteEvent(eventId);
		if(result != 0){
			LoginDAO loginDAO = new LoginDAO();
			ArrayList<EventBean> eventBeans =  loginDAO.fetchEvents();
			session.setAttribute("eventDetails", eventBeans);
			out.print("Successfully deleted");
		}else{
			out.print("Failed to delete");
		}
	}

}
