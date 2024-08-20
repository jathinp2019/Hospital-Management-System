package com.hospital.management.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImportUserServlet
 */
@WebServlet("/ImportUserServlet")
public class ImportUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        JSONObject jsonResponse = new JSONObject();

	        try {
	            Part filePart = request.getPart("importFile");
	            if (filePart != null) {
	                InputStream fileContent = filePart.getInputStream();
	                String content = new String(fileContent.readAllBytes(), StandardCharsets.UTF_8);

	                // Simulate processing of the file
	                if (!content.isEmpty()) {
	                    // Here you would typically parse the file and insert the data into the database
	                    jsonResponse.put("success", true);
	                } else {
	                    jsonResponse.put("success", false);
	                }
	            } else {
	                jsonResponse.put("success", false);
	            }
	        } catch (Exception e) {
	            jsonResponse.put("success", false);
	        }
	        
	        out.print(jsonResponse);
	        out.flush();
	    
	
	}

}
