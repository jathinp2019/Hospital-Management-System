package com.hospital.management.controller;


import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserSignupServlet")
public class UserSignupServlet extends HttpServlet {

    // In-memory user storage
     static final JSONObject users = new JSONObject();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Parse JSON request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject jsonRequest = new JSONObject(sb.toString());

        String name = jsonRequest.getString("name");
        String email = jsonRequest.getString("email");
        String password = jsonRequest.getString("password");
        String contact = jsonRequest.getString("contact");

        // Create a new user JSON object
        JSONObject user = new JSONObject();
        user.put("name", name);
        user.put("password", password);
        user.put("contact", contact);

        JSONObject jsonResponse = new JSONObject();

        // Check if the email already exists
        if (getUsers().has(email)) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "User already exists. Please login.");
        } else {
            // Store user in the in-memory storage
            getUsers().put(email, user);
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Signup successful.");
        }

        out.print(jsonResponse.toString());
        out.flush();
    }

	public static JSONObject getUsers() {
		return users;
	}

}
