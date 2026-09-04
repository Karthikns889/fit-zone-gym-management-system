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
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewMemberships")
public class ViewMembershipServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("login.html");
            return;
        }

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>FitZone - My Membership</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");

        out.println("<body>");

        out.println("<header>");
        out.println("<div class='logo'>FITZONE</div>");

        out.println("<nav>");
        out.println("<a href='member-dashboard.html'>Dashboard</a>");
        out.println("<a href='index.html'>Logout</a>");
        out.println("</nav>");

        out.println("</header>");

        out.println("<section class='page-section'>");

        out.println("<h2>My Membership</h2>");

        try {

            Connection con = DBconnection.getConnection();

            String sql = "SELECT * FROM memberships";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.println("<div class='member-form'>");

                out.println("<h3>"
                        + rs.getString("plan_name")
                        + "</h3>");

                out.println("<p><strong>Duration:</strong> "
                        + rs.getString("duration")
                        + "</p>");

                out.println("<p><strong>Price:</strong> ₹"
                        + rs.getDouble("price")
                        + "</p>");

                out.println("<p><strong>Description:</strong> "
                        + rs.getString("description")
                        + "</p>");

                out.println("</div>");

                out.println("<br>");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            out.println("<p>Error: "
                    + e.getMessage()
                    + "</p>");
        }

        out.println("<br>");

        out.println("<a href='member-dashboard.html' class='btn'>");
        out.println("Back to Dashboard");
        out.println("</a>");

        out.println("</section>");

        out.println("<footer>");
        out.println("<p>© 2026 FitZone Gym Management System</p>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}