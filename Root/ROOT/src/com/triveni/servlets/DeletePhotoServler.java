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
import com.triveni.beans.PhotoBean;
@WebServlet("/DeletePhotoServler")
public class DeletePhotoServler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeletePhotoServler() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String PhotoId = request.getParameter("id");
		ChangeUserDAO changeUserDAO = new ChangeUserDAO();
		PrintWriter out = response.getWriter();
		int result=changeUserDAO.deletePhoto(PhotoId);
		if(result != 0){
			LoginDAO loginDAO = new LoginDAO();
			ArrayList<PhotoBean> photoBeans = loginDAO.fetchPhotos();
			session.setAttribute("photoDetails", photoBeans);
			out.print("Successful");
		}else{
			out.print("failure");
		}
	}

}
