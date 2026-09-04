package com.FitZone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addPayment")
public class AddPaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String memberName = request.getParameter("member_name");
        String plan = request.getParameter("plan");
        String amount = request.getParameter("amount");
        String paymentMethod = request.getParameter("payment_method");
        String paymentDate = request.getParameter("payment_date");

        String sql = "INSERT INTO payments " +
                     "(member_name, plan, amount, payment_method, payment_date) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, memberName);
            ps.setString(2, plan);
            ps.setString(3, amount);
            ps.setString(4, paymentMethod);
            ps.setString(5, paymentDate);

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect("payments.html");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}