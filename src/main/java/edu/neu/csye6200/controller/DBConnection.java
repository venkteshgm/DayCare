/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jasonpauldarivemula
 */
public class DBConnection {
    public static Connection getConnection() {
        try {
            Connection con;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://database-1.cog3jk7pua93.us-east-2.rds.amazonaws.com:3306/daycaredb", "admin",
                    "Namrataisbad");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
}
