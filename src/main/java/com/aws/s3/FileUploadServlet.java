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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendRedirect(req.getContextPath() + "/upload.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String description = req.getParameter("description");
		System.out.println("Description: " + description);
		
		Part part = req.getPart("file");
		
		String fileName = "e6drmmcymx3m/public/jacobin/" + getFileName(part);
		
		System.out.println("fileName: " + fileName);
		
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
	
	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		int beginIndex = contentDisposition.indexOf("filename=") + 10;
		int endIndex = contentDisposition.length() - 1;
		return contentDisposition.substring(beginIndex, endIndex);
	}
}
