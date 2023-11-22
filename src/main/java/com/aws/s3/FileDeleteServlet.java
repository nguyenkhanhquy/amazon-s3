package com.aws.s3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/delete"})
public class FileDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String AWS_URL_FOLDER = System.getenv("CLOUDCUBE_URL_FOLDER");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.sendRedirect(req.getContextPath() + "/delete.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String fileName = req.getParameter("fileName");
		
		fileName = AWS_URL_FOLDER + fileName;
		
		S3Util.deleteFile(fileName);
		
		String message = "Your file has been deleted successfully";
		
		req.setAttribute("message", message);
		req.getRequestDispatcher("message.jsp").forward(req, resp);
	}
}
