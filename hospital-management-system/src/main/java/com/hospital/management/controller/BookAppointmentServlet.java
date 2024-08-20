package com.hospital.management.controller;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject jsonRequest = new JSONObject(sb.toString());

        String doctor = jsonRequest.getString("doctor");
        String date = jsonRequest.getString("date");
        String time = jsonRequest.getString("time");
        String user = jsonRequest.getString("user");

        JSONObject jsonResponse = new JSONObject();

        // Hardcoded schedules for doctors
        JSONObject doctorSchedules = new JSONObject();
        doctorSchedules.put("dr_smith", new String[]{"08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"});
        doctorSchedules.put("dr_jones", new String[]{"08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"});
        doctorSchedules.put("dr_williams", new String[]{"08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"});

        if (doctorSchedules.has(doctor) && isValidTime(doctorSchedules.getJSONArray(doctor), time)) {
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Appointment booked successfully with " + doctor + " on " + date + " at " + time + ".");
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Selected time is not available. Please choose a different time.");
        }

        response.getWriter().print(jsonResponse.toString());
    }

    private boolean isValidTime(org.json.JSONArray availableTimes, String selectedTime) {
        for (int i = 0; i < availableTimes.length(); i++) {
            if (availableTimes.getString(i).equals(selectedTime)) {
                return true;
            }
        }
        return false;
    }
}
