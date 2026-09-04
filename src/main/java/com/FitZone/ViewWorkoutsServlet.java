package com.FitZone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewWorkouts")
public class ViewWorkoutsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String sql = "SELECT * FROM workouts";

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>FitZone - Workout Plans</title>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("</head>");

            out.println("<body>");

            out.println("<header>");
            out.println("<h1>FITZONE</h1>");

            out.println("<nav>");
            out.println("<a href='index.html'>Home</a>");
            out.println("<a href='dashboard.html'>Dashboard</a>");
            out.println("<a href='workouts.html'>Workouts</a>");
            out.println("</nav>");

            out.println("</header>");

            out.println("<section class='page-section'>");

            out.println("<h2>Workout Plans</h2>");

            out.println("<table>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Plan Name</th>");
            out.println("<th>Type</th>");
            out.println("<th>Duration</th>");
            out.println("<th>Difficulty</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" +
                        rs.getInt("workout_id") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("plan_name") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("workout_type") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("duration") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("difficulty") +
                        "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<br>");

            out.println("<a href='workouts.html' class='btn'>Back to Workouts</a>");

            out.println("</section>");

            out.println("<footer>");
            out.println("<p>© 2026 FitZone Gym Management System</p>");
            out.println("</footer>");

            out.println("</body>");
            out.println("</html>");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}