package com.FitZone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addWorkout")
public class AddWorkoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String planName = request.getParameter("plan_name");
        String workoutType = request.getParameter("workout_type");
        String duration = request.getParameter("duration");
        String difficulty = request.getParameter("difficulty");

        String sql = "INSERT INTO workouts " +
                     "(plan_name, workout_type, duration, difficulty) " +
                     "VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, planName);
            ps.setString(2, workoutType);
            ps.setString(3, duration);
            ps.setString(4, difficulty);

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect("workouts.html");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}