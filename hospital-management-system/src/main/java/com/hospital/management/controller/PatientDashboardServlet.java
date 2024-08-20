package com.hospital.management.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PatientDashboardServlet")
public class PatientDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Sample Data
        JSONArray appointmentsArray = new JSONArray();

        JSONObject appointment1 = new JSONObject();
        appointment1.put("doctor", "Dr. Smith");
        appointment1.put("date", "2024-08-20");
        appointment1.put("time", "10:00 AM");
        appointment1.put("status", "Confirmed");

        JSONObject appointment2 = new JSONObject();
        appointment2.put("doctor", "Dr. Jones");
        appointment2.put("date", "2024-08-21");
        appointment2.put("time", "02:00 PM");
        appointment2.put("status", "Pending");

        appointmentsArray.put(appointment1);
        appointmentsArray.put(appointment2);

        out.print(appointmentsArray);
        out.flush();
    }
}
