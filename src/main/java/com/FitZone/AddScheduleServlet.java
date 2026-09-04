package com.FitZone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addSchedule")
public class AddScheduleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String className = request.getParameter("class_name");
        String trainer = request.getParameter("trainer");
        String date = request.getParameter("schedule_date");
        String time = request.getParameter("schedule_time");
        String duration = request.getParameter("duration");

        String sql = "INSERT INTO schedules " +
                     "(class_name, trainer, schedule_date, schedule_time, duration) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, className);
            ps.setString(2, trainer);
            ps.setString(3, date);
            ps.setString(4, time);
            ps.setString(5, duration);

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect("schedule.html");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}