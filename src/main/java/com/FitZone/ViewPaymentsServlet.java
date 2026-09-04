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

@WebServlet("/viewPayments")
public class ViewPaymentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String sql = "SELECT * FROM payments";

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            out.println("<html><body>");
            out.println("<h2>FitZone Payment History</h2>");

            out.println("<table border='1'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Member</th>");
            out.println("<th>Plan</th>");
            out.println("<th>Amount</th>");
            out.println("<th>Method</th>");
            out.println("<th>Date</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("payment_id") + "</td>");
                out.println("<td>" + rs.getString("member_name") + "</td>");
                out.println("<td>" + rs.getString("plan") + "</td>");
                out.println("<td>₹" + rs.getString("amount") + "</td>");
                out.println("<td>" + rs.getString("payment_method") + "</td>");
                out.println("<td>" + rs.getString("payment_date") + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<br>");
            out.println("<a href='payments.html'>Back to Payments</a>");

            out.println("</body></html>");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}