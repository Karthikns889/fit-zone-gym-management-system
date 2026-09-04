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

@WebServlet("/viewSchedules")
public class ViewSchedulesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String sql = "SELECT * FROM schedules";

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>FitZone - Schedule</title>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("</head>");

            out.println("<body>");

            out.println("<header>");
            out.println("<h1>FITZONE</h1>");

            out.println("<nav>");
            out.println("<a href='index.html'>Home</a>");
            out.println("<a href='dashboard.html'>Dashboard</a>");
            out.println("<a href='schedule.html'>Schedule</a>");
            out.println("</nav>");

            out.println("</header>");

            out.println("<section class='page-section'>");

            out.println("<h2>Gym Schedule</h2>");

            out.println("<table>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Class</th>");
            out.println("<th>Trainer</th>");
            out.println("<th>Date</th>");
            out.println("<th>Time</th>");
            out.println("<th>Duration</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" +
                        rs.getInt("schedule_id") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("class_name") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("trainer") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("schedule_date") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("schedule_time") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("duration") +
                        "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<br>");

            out.println("<a href='schedule.html' class='btn'>Back to Schedule</a>");

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