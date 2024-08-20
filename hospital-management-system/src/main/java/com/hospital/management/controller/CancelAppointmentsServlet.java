package com.hospital.management.controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CancelAppointmentsServlet")
public class CancelAppointmentsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String date = jsonRequest.getString("date");
            String doctor = jsonRequest.getString("doctor");

            // Simulate cancellation logic
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Appointments for " + doctor + " on " + date + " have been canceled.");
        } catch (Exception e) {
            jsonResponse.put("success", false);
        }
        
        out.print(jsonResponse);
        out.flush();
    }}