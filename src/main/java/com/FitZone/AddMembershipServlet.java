package com.FitZone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addMembership")
public class AddMembershipServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String planName = request.getParameter("planName");
        String duration = request.getParameter("duration");
        String price = request.getParameter("price");
        String description = request.getParameter("description");

        String sql = "INSERT INTO memberships (plan_name, duration, price, description) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, planName);
            ps.setString(2, duration);
            ps.setString(3, price);
            ps.setString(4, description);

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect("memberships.html");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}