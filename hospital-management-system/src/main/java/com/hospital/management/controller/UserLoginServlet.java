package com.hospital.management.controller;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.hospital.management.controller.UserSignupServlet;
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    // Reference the same in-memory user storage
    private static final JSONObject users = UserSignupServlet.getUsers();

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

        String email = jsonRequest.getString("email");
        String password = jsonRequest.getString("password");

        JSONObject jsonResponse = new JSONObject();

        // Check if user exists and password matches
        if (users.has(email) && users.getJSONObject(email).getString("password").equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", users.getJSONObject(email).getString("name"));

            jsonResponse.put("success", true);
            jsonResponse.put("message", "Login successful.");
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Invalid email or password.");
        }

        out.print(jsonResponse.toString());
        out.flush();
    }
}
