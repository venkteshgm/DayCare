/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.services;

import edu.neu.csye6200.controller.DBConnection;
import edu.neu.csye6200.objects.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jasonpauldarivemula
 */
public class TeacherService {
    
     public static int insertTeacher(Teacher t){
        Connection con = DBConnection.getConnection();
        int teacherId=-1;
        
        if(con!=null){
            try {
                
                String query = "insert into Teacher (firstname,lastname,gender,agegroupId,lastreviewdate) values (?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1,t.getFirstName());
                stmt.setString(2,t.getLastName());
                stmt.setString(3, t.getGender());
                stmt.setInt(4,t.getAgegroupId());
                stmt.setString(5,t.getReviewdate());
               
                
                stmt.executeUpdate();  
                
                ResultSet rs = stmt.getGeneratedKeys();
                
                rs.first();
                teacherId = rs.getInt(1);
                
                stmt.close();
                
                return teacherId;
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        
        return -1;
        
    }
     public static List<Teacher> getTeacherAnniversary(){
        List<Teacher> imunizationList = new ArrayList<Teacher>();
        try{
        Connection con = DBConnection.getConnection();
        Calendar calendarEnd=Calendar.getInstance();
//        System.out.println(new Date(calendarEnd));
        System.out.println(calendarEnd.get(Calendar.MONTH));
         // You can substract from the current Year to get the previous year last dates.
         calendarEnd.set(Calendar.YEAR,calendarEnd.get(Calendar.YEAR)-1);

         String endDateStr = calendarEnd.get(Calendar.YEAR)+"-"+(calendarEnd.get(Calendar.MONTH)+1)+"-"+calendarEnd.get(Calendar.DAY_OF_MONTH);
         
          System.out.println(endDateStr);
      
       Statement stmt = con.createStatement();
       
       ResultSet rs =  stmt.executeQuery(String.format("Select * from Teacher where lastreviewdate=\"%s\"",endDateStr));
//       List<String> list = new ArrayList<String>();
       while(rs.next()) {
            imunizationList.add(new Teacher(Integer.parseInt(rs.getString("agegroupId")), rs.getString("firstname"), rs.getString("lastname"), rs.getString("gender"), rs.getString("lastreviewdate")));
       }
       System.out.println(imunizationList);
        } catch( SQLException ex){
            System.out.println(ex.getMessage());
            return new ArrayList<Teacher>();
        }
       return imunizationList;
	}
     
     public static List<Teacher> getUnassignedTeachers(){
        Connection con = DBConnection.getConnection();
        
        if(con!=null){
            try {
                
                String query = "Select * from Teacher where agegroupId=0";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//                stmt.setString(1,t.getFirstNamxe());
               
                
                ResultSet rs = stmt.executeQuery();  
                List<Teacher> list = new ArrayList<Teacher>();
//                stmt.getGeneratedKeys();
                while(rs.next()){
                   Teacher t =  new Teacher(rs.getInt(5), rs.getString(2), rs.getString("lastname"), rs.getString("gender"), rs.getString(5)); 
                   t.setTeacherID(rs.getInt(1));
                   list.add(t);
                }
                stmt.close();
                return list;
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return new ArrayList<Teacher>();
            }
        }
        return new ArrayList<Teacher>();
        
    }
     
     public static List<Teacher> getTeachers(){
         //method to get List of all Teachers 
         Connection con = DBConnection.getConnection();
         return getTeachers(con);
     }
     
     public static List<Teacher> getTeachers(Connection con){
         if(con!=null){
            try {
                
                String query = "Select * from Teacher";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.executeQuery();  
                List<Teacher> list = new ArrayList<Teacher>();
                while(rs.next()){
                   Teacher t =  new Teacher(rs.getInt(5), rs.getString(2), rs.getString("lastname"), rs.getString("gender"), rs.getString("lastreviewdate")); 
                   t.setTeacherID(rs.getInt(1));
                   list.add(t);
                }
                stmt.close();
                return list;
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return new ArrayList<Teacher>();
            }
        }
        return new ArrayList<Teacher>();
     }
     
     public static void updateGroupTeachers(int agegroupNumber, int teacherId){
        Connection con = DBConnection.getConnection();
        
        if(con!=null){
            try {
                
                String query = "Update Teacher SET agegroupId = ? Where teacherId = ?";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1,agegroupNumber);
                stmt.setInt(2,teacherId);
                
               stmt.executeUpdate();  
               
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
     public static Teacher getTeacherFromTeacherId(int teacherId){
         Connection con = DBConnection.getConnection();
         Teacher t;
         if(con!=null){
            try {
                
                String query = "Select * from Teacher where teacherId=" + teacherId;
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                   t =  new Teacher(rs.getInt(5), rs.getString(2), rs.getString("lastname"), rs.getString("gender"), rs.getString("lastreviewdate")); 
                   t.setTeacherID(rs.getInt(1));
                   return t;
                }
                
                return null;
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
     }
}
