package com.triveni.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.triveni.DAO.LoginDAO;
import com.triveni.DAO.ProfileDAO;
import com.triveni.beans.ItenaryBean;

@WebServlet("/UploadItenaryServlet")
public class UploadItenaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 5000 * 1024;
	private int maxMemSize = 40 * 1024;
	private File file;
	public void init() {
		//filePath = getServletContext().getInitParameter("file-upload");
		filePath = System.getenv("OPENSHIFT_DATA_DIR")+"itenary-uploads/";
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String arrivalDate = "";
		String arrivalTime = "";
		String extension = "";
		String phone = "";
		String extraInfo = "";
		String fileStoragePath = "";
		String fileName = "";
		String accommodationRequired = "";
		String mins ="";
		String AMPM = "";
		String triveniId = (String) session.getAttribute("triveniId");
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.getWriter();
		if (!isMultipart) {
			// File upload failure return statements here
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		//factory.setRepository(new File("c:\\temp"));
		factory.setRepository(new File(System.getenv("OPENSHIFT_DATA_DIR")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		try {
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					fi.getFieldName();
					fileName = fi.getName();
					String contentType = fi.getContentType();
					fi.isInMemory();
					fi.getSize();
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					long now = System.currentTimeMillis();
					String append = now+"";
					File renamedFile = new File(filePath+triveniId+append+"."+FilenameUtils.getExtension(fileName));
					fi.write(file);
					System.out.println(file.renameTo(renamedFile));
					//fileStoragePath = "../data/"+triveniId+append+"."+FilenameUtils.getExtension(fileName);
					fileStoragePath = "/static/itenary-uploads/"+triveniId+append+"."+FilenameUtils.getExtension(fileName);
				} else if (fi.isFormField()) // your code for getting form fields
				{
					String name = fi.getFieldName();
					if (name.equals("arrivalDate")) {
						arrivalDate = fi.getString();
					} else if (name.equals("arrivalTime")) {
						arrivalTime = fi.getString();
					} else if (name.equals("extension")) {
						extension = "+" + fi.getString();
					} else if (name.equals("phone")) {
						phone =  fi.getString();
					} else if (name.equals("extraInfo")) {
						extraInfo = fi.getString();
					}else if(name.equals("accommodationRequired")){
						accommodationRequired = fi.getString();
					}else if(name.equals("minutes")){
						mins = fi.getString();
					}else if(name.equals("AMPM")){
						AMPM = fi.getString();
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		ProfileDAO profileDAO = new ProfileDAO();
		profileDAO.uploadItenary(triveniId, arrivalDate, (arrivalTime+" "+mins+" "+AMPM), (extension + phone), extraInfo, fileStoragePath,accommodationRequired);
		LoginDAO loginDAO = new LoginDAO();
		ItenaryBean itenaryBean = loginDAO.checkItenaryDetails(triveniId);
		session.setAttribute("itenaryDetails", itenaryBean);
		response.sendRedirect("userprofile/pickupform.jsp");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with " + getClass().getName() + ": POST method required.");
	}
}