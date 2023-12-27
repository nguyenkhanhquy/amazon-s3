package com.aws.s3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 11 // 11MB
		)
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String AWS_URL_FOLDER = System.getenv("AWS_URL_FOLDER");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendRedirect(req.getContextPath() + "/upload.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Part part = req.getPart("file");
		
		String originalFileName = S3Util.getFileName(part);
		
		String newFileName = "a" + originalFileName.substring(originalFileName.lastIndexOf('.'));
		
		String fileName = AWS_URL_FOLDER + newFileName;
		
		String message = "";

		try {
			S3Util.uploadFile(fileName, part.getInputStream());
			message = "Your file has been uploaded successfully";
		} catch (Exception ex) {
			message = "Error uploading file: " + ex.getMessage();
		}
		
		req.setAttribute("message", message);
		req.getRequestDispatcher("message.jsp").forward(req, resp);
	}
}
