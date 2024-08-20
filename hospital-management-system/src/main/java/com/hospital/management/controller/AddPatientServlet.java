package com.hospital.management.controller;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/AddPatientServlet")
public class AddPatientServlet extends HttpServlet {
    private List<JSONObject> patients = new ArrayList<>();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        JSONObject formObject = new JSONObject();
        formObject.put("html", 
        	    "<form id='addPatientForm' style='max-width: 500px; margin: auto; padding: 20px; background-color: #f4f6f8; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);'>" +
        	    "<label for='name' style='display: block; font-weight: bold; margin-bottom: 8px;'>Name:</label>" +
        	    "<input type='text' id='name' name='name' required style='width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px;'>" +

        	    "<label for='age' style='display: block; font-weight: bold; margin-bottom: 8px;'>Age:</label>" +
        	    "<input type='number' id='age' name='age' required style='width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px;'>" +

        	    "<label for='history' style='display: block; font-weight: bold; margin-bottom: 8px;'>Medical History:</label>" +
        	    "<textarea id='history' name='history' style='width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px;'></textarea><br>" +

        	    "<label for='allergies' style='display: block; font-weight: bold; margin-bottom: 8px;'>Allergies:</label>" +
        	    "<textarea id='allergies' name='allergies' style='width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px;'></textarea><br>" +

        	    "<label for='contact' style='display: block; font-weight: bold; margin-bottom: 8px;'>Contact Number:</label>" +
        	    "<input type='tel' id='contact' name='contact' required style='width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 4px;'>" +

        	    "<button type='submit' style='width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;'>Submit</button>" +
        	    "</form>" +
        	    "<div id='message' style='margin-top: 20px; text-align: center;'></div>"
        	);


        response.getWriter().print(formObject.toString());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            // Read JSON from request
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }

            JSONObject patientData = new JSONObject(sb.toString());

            // Add the new patient data to the list
            patients.add(patientData);

            // Create response JSON
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Patient added successfully.");

            // Return success response
            response.getWriter().print(jsonResponse.toString());

        } catch (Exception e) {
            // Return error response
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "An error occurred while adding patient.");

            response.getWriter().print(jsonResponse.toString());
        }
    }
}
