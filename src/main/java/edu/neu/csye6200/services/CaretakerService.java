/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.services;

import edu.neu.csye6200.controller.DBConnection;
import edu.neu.csye6200.objects.CareTaker;
import edu.neu.csye6200.objects.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jasonpauldarivemula
 */
public class CaretakerService {

    public static int insertCaretaker(CareTaker ct) {
        Connection con = DBConnection.getConnection();
        int caretakerId = -1;
        if (con != null) {
            try {
                String query = "insert into Caretaker (firstname,lastname,address,phonenumber,gender) values (?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, ct.getFirstName());
                stmt.setString(2, ct.getLastName());
                stmt.setString(3, ct.getAddress());
                stmt.setString(4, ct.getPhone());
                stmt.setString(5, ct.getGender());

                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();

                rs.first();
                caretakerId = rs.getInt(1);
               
                stmt.close();
                return caretakerId;

            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
 
        }
        return -1;

    }

    public static CareTaker getCaretakerFromPhone(String phone) {
        Connection con = DBConnection.getConnection();
        CareTaker ct = null;
        if (con != null) {
            try {
                String query = "select * from Caretaker where phonenumer=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, phone);

                ResultSet rs = stmt.executeQuery();

                int cnt = 0;

                while (rs.next() && cnt == 0) {
                    String address = rs.getString("address");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String gender = rs.getString("gender");
                    int id = rs.getInt("caretakerId");

                    ct = new CareTaker(address, phone, id, firstname, lastname, gender);

                    cnt++;
                }

            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class
                        .getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        }

        return ct;
    }
    
    
    public static CareTaker getCaretakerFromCaretakerId(int caretakerId) {
        Connection con = DBConnection.getConnection();
        CareTaker ct = null;
        if (con != null) {
            try {
                String query = "select * from Caretaker where caretakerId=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, caretakerId);

                ResultSet rs = stmt.executeQuery();

                int cnt = 0;

                while (rs.next() && cnt == 0) {
                    String address = rs.getString("address");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String gender = rs.getString("gender");
                    String phone = rs.getString("phonenumber");
                    int id = rs.getInt("caretakerId");

                    ct = new CareTaker(address, phone, id, firstname, lastname, gender);
                    cnt++;
                }

            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class
                        .getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        }

        return ct;
    }
    
    public static List<CareTaker> getCaretakers(){
        Connection con = DBConnection.getConnection();
         if(con!=null){
            try {
                
                String query = "Select * from Caretaker";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.executeQuery();  
                List<CareTaker> list = new ArrayList<>();
                while(rs.next()){
                    //CareTaker(String address, String phone, int careTakerId, String firstName, String lastName, String gender)
                   CareTaker ct =  new CareTaker(rs.getString(4), rs.getString(5), rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6));
                   
                   list.add(ct);
                }
                stmt.close();
                return list;
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return new ArrayList<CareTaker>();
            }
        }
        return new ArrayList<CareTaker>();
     }
    

}
