package com.hospital.management.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ShowPatientsServlet")
public class ShowPatientsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONArray patientsArray = new JSONArray();

        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonRequest = new JSONObject(sb.toString());
            String filter = jsonRequest.getString("filter");

            // Simulate fetching patient data based on filter
            if (filter.equals("all") || filter.equals("recent")) {
                JSONObject patient1 = new JSONObject();
                patient1.put("name", "John Doe");
                patient1.put("age", 30);
                patient1.put("condition", "Flu");

                JSONObject patient2 = new JSONObject();
                patient2.put("name", "Jane Smith");
                patient2.put("age", 25);
                patient2.put("condition", "Checkup");

                patientsArray.put(patient1);
                patientsArray.put(patient2);
            }

            // Send the JSON response
            if (patientsArray.length() > 0) {
                out.print(patientsArray.toString());
            } else {
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("success", false);
                jsonResponse.put("message", "No patients found.");
                out.print(jsonResponse.toString());
            }

        } catch (Exception e) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "An error occurred while processing the request.");
            out.print(jsonResponse.toString());
        } finally {
            out.flush();
            out.close();
        }
    }}
