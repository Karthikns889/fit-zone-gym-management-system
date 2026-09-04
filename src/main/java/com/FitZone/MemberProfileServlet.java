package com.FitZone;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/memberProfile")
public class MemberProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");
        String phone = (String) session.getAttribute("phone");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>FitZone - My Profile</title>");
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

        out.println("<h2>My Profile</h2>");

        out.println("<div class='member-form'>");

        out.println("<h3>Personal Details</h3>");

        out.println("<p><strong>Name:</strong> " + name + "</p>");
        out.println("<p><strong>Email:</strong> " + email + "</p>");
        out.println("<p><strong>Phone:</strong> " + phone + "</p>");

        out.println("</div>");

        out.println("<br>");

        out.println("<a href='member-dashboard.html' class='btn'>Back to Dashboard</a>");

        out.println("</section>");

        out.println("<footer>");
        out.println("<p>© 2026 FitZone Gym Management System</p>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}