package com.triveni.servlets;

import java.io.IOException;
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

@WebServlet("/UpdateEventServlet")
public class UpdateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public UpdateEventServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventName,eventDate,eventTime,venue,description,eventId;
		HttpSession session = request.getSession();
		eventId = request.getParameter("eventId");
		eventDate = request.getParameter("eventDate");
		venue = request.getParameter("venue");
		description = request.getParameter("description");
		eventTime = request.getParameter("eventTime");
		eventName = request.getParameter("eventName");
		EventBean eventBean = new EventBean();
		eventBean.setEventDate(eventDate);
		eventBean.setVenue(venue);
		eventBean.setEventDescription(description);
		eventBean.setEventName(eventName);
		eventBean.setEventTime(eventTime);
		eventBean.setEventId(Integer.parseInt(eventId));
		ChangeUserDAO changeUserDAO = new ChangeUserDAO();
		int result = changeUserDAO.updateEvent(eventBean);
		if(result != 0){
			LoginDAO loginDAO = new LoginDAO();
			ArrayList<EventBean> eventBeans = loginDAO.fetchEvents();
			session.setAttribute("eventDetails", eventBeans);
			response.sendRedirect("userprofile/updateEvents.jsp?status=Succesful&id="+eventId);
		}else
		{
			response.sendRedirect("userprofile/updateEvents.jsp?status=Failure&id="+eventId);
		}
	}

}
