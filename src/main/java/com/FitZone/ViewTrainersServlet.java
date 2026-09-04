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

@WebServlet("/viewTrainers")
public class ViewTrainersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String sql = "SELECT * FROM trainers";

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>FitZone - Trainers</title>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("</head>");

            out.println("<body>");

            out.println("<header>");
            out.println("<h1>FITZONE</h1>");
            out.println("<nav>");
            out.println("<a href='index.html'>Home</a>");
            out.println("<a href='dashboard.html'>Dashboard</a>");
            out.println("<a href='trainers.html'>Trainers</a>");
            out.println("</nav>");
            out.println("</header>");

            out.println("<section class='page-section'>");

            out.println("<h2>Trainer List</h2>");

            out.println("<table>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Phone</th>");
            out.println("<th>Specialization</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" +
                        rs.getInt("trainer_id") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("name") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("email") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("phone") +
                        "</td>");

                out.println("<td>" +
                        rs.getString("specialization") +
                        "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<br>");

            out.println("<a href='trainers.html' class='btn'>Back to Trainers</a>");

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