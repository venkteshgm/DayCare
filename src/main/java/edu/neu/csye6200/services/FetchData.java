/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.services;

import edu.neu.csye6200.controller.DBConnection;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Venktesh
 */
public class FetchData {
    public static ResultSet SelectQuery(String query){
        Connection con = DBConnection.getConnection();
        ResultSet result = SelectQuery(con, query);
        return result;
    }
    
    public static ResultSet SelectQuery(Connection con, String query){
        if(con!=null){
            try {
//                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                Statement stmt = con.createStatement();
                ResultSet result = stmt.executeQuery(query);
                return result;
            }
            catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        else{
            return null;
        }
    }
    
}
