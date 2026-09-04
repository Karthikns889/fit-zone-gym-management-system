package com.FitZone;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        Connection con = DBconnection.getConnection();

        if (con != null) {
            System.out.println("FitZone Database Connected!");
        } else {
            System.out.println("Database Connection Failed!");
        }
    }
}