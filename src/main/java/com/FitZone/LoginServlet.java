package com.FitZone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {

            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // Create login session
                HttpSession session = request.getSession();

                // Store user information
                session.setAttribute("user_id", rs.getInt("user_id"));
                session.setAttribute("name", rs.getString("name"));
                session.setAttribute("email", rs.getString("email"));
                session.setAttribute("phone", rs.getString("phone"));

                // Get user role
                String role = rs.getString("role");

                session.setAttribute("role", role);

                // Redirect according to role
                if ("admin".equalsIgnoreCase(role)) {

                    response.sendRedirect("admin-dashboard.html");

                } else {

                    response.sendRedirect("member-dashboard.html");
                }

            } else {

                response.setContentType("text/html;charset=UTF-8");

                response.getWriter().println(
                    "<h2>Invalid Email or Password</h2>"
                );

                response.getWriter().println(
                    "<a href='login.html'>Try Again</a>"
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                "<h2>Error occurred</h2>"
            );

            response.getWriter().println(
                "<p>" + e.getMessage() + "</p>"
            );
        }
    }
}