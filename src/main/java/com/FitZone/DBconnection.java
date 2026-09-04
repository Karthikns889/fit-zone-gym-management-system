package com.FitZone;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

    private static final String URL = "jdbc:mysql://localhost:3306/FitZone";
    private static final String USER = "root";
    private static final String PASSWORD = "6300";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            System.out.println("Database Connected Successfully!");

            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}