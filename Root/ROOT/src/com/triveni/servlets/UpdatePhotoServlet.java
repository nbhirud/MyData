package com.triveni.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import com.triveni.DAO.ChangeUserDAO;
import com.triveni.DAO.LoginDAO;
import com.triveni.beans.PhotoBean;

@WebServlet("/UpdatePhotoServlet")
public class UpdatePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 5000 * 1024;
	private int maxMemSize = 40 * 1024;
	private File file;

	public void init() {
		// filePath = getServletContext().getInitParameter("file-upload");
		filePath = System.getenv("OPENSHIFT_DATA_DIR") + "gallery/";
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String photoName = "";
		String photoId = "";
		String description = "";
		String fileStoragePath = "";
		String fileName = "";
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.getWriter();
		if (!isMultipart) {
			// File upload failure return statements here
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		// factory.setRepository(new File("c:\\temp"));
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
					String append = now + "";
					File renamedFile = new File(filePath + append + "." + FilenameUtils.getExtension(fileName));
					fi.write(file);
					System.out.println(file.renameTo(renamedFile));
					fileStoragePath = "/static/gallery/" + append + "." + FilenameUtils.getExtension(fileName);
				} else if (fi.isFormField()) // your code for getting form
												// fields
				{
					String name = fi.getFieldName();
					if (name.equals("photoName")) {
						photoName = fi.getString();
					} else if (name.equals("description")) {
						description = fi.getString();
					} else if (name.equals("photoId")) {
						photoId = fi.getString();
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		PhotoBean photoBean = new PhotoBean();
		photoBean.setPhotoName(photoName);
		photoBean.setDescription(description);
		photoBean.setUrl(fileStoragePath);
		photoBean.setPhotoId(photoId);
		ChangeUserDAO changeUserDAO = new ChangeUserDAO();
		int result = changeUserDAO.updatePhotos(photoBean);
		if (result != 0) {
			LoginDAO loginDAO = new LoginDAO();
			ArrayList<PhotoBean> photoBeans = loginDAO.fetchPhotos();
			session.setAttribute("photoDetails", photoBeans);
			response.sendRedirect("userprofile/updatePhoto.jsp?status=Successful&id=" + photoId);
		} else {
			response.sendRedirect("userprofile/uploadPhotos.jsp?status=Failure&id=" + photoId);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with " + getClass().getName() + ": POST method required.");
	}

}
