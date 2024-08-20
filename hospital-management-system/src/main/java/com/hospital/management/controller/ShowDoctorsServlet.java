package com.hospital.management.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet implementation class ShowDoctorsServlet
 */
@WebServlet("/ShowDoctorsServlet")
public class ShowDoctorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDoctorsServlet() {
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
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonRequest = new JSONObject(sb.toString());
            String doctor = jsonRequest.getString("doctor");

            String schedule;
            switch (doctor) {
                case "dr_smith":
                    schedule = "Dr. Smith's Schedule: 9 AM - 5 PM, Mon-Fri";
                    break;
                case "dr_jones":
                    schedule = "Dr. Jones' Schedule: 10 AM - 6 PM, Mon-Fri";
                    break;
                case "dr_williams":
                    schedule = "Dr. Williams' Schedule: 8 AM - 4 PM, Mon-Fri";
                    break;
                default:
                    schedule = "No schedule available.";
                    break;
            }

            jsonResponse.put("success", true);
            jsonResponse.put("schedule", schedule);
        } catch (Exception e) {
            jsonResponse.put("success", false);
        }
        
        out.print(jsonResponse);
        out.flush();
    }	}


