/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.services;

import edu.neu.csye6200.objects.Student;
import edu.neu.csye6200.controller.DBConnection;
import edu.neu.csye6200.objects.AgeGroupEnum;
import edu.neu.csye6200.services.FetchData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author jasonpauldarivemula
 */
public class StudentService {
    public static int insertStudent(Student st){
        Connection con = DBConnection.getConnection();
        int studentId=-1;
        
        if(con!=null){
            try {
                
//                String query = "insert into Student (firstname,lastname,age,gender,caretakerId,groupid) values (?,?,?,?,?,?)";
                String query = "insert into Student "
                        + "(age,address,phonenumber,registrationdate,firstname,lastname,gender,caretakerId,groupid) values "
                        + "(?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1,st.getAge());
                stmt.setString(2,st.getAddress());
                stmt.setString(3,st.getPhone());
                stmt.setString(4, st.getRegistrationDate());
                stmt.setString(5, st.getFirstName());
                stmt.setString(6, st.getLastName());
                stmt.setString(7, st.getGender());
                stmt.setInt(8,st.getCaretakerID());
                stmt.setInt(9,st.getGroupID());
                
                stmt.executeUpdate();  
                
                ResultSet rs = stmt.getGeneratedKeys();
                AgeGroupService.decrementCapacity(con , st.getGroupID());

                rs.first();
                studentId = rs.getInt(1);
                
                stmt.close();
                
                return studentId;
                
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        
        return -1;
        
    }
    
    public static List<Student> fetchStudentData(){
        //to fetch all student data
        Connection con = DBConnection.getConnection();
        List<Student> studentList = new ArrayList<>();
        if(con!=null){
            
                String query = "SELECT * FROM daycaredb.Student;";
                ResultSet rs = FetchData.SelectQuery(con, query);
                studentList = arrangeStudentData(rs);
                
//                ResultSetMetaData rsmd = rs.getMetaData();
                
            
        }
        return studentList;
    }
    public static List<Student> fetchStudentData(AgeGroupEnum ageGroup){
        //to fetch students of a particular age group based on the passed AgeGroupEnum
        Connection con = DBConnection.getConnection();
        List<Student> studentList = new ArrayList<>();
        if(con!=null){
                int minAge = ageGroup.getMinLimitInMonths();
                int maxAge = ageGroup.getMaxLimitInMonths();
                
                String query = "SELECT * FROM daycaredb.Student where age between "+minAge+" and "+maxAge+";";
                ResultSet rs = FetchData.SelectQuery(con, query);
                
                  studentList = arrangeStudentData(rs);
            
        }
        return studentList;
    }
    public static List<Student> fetchStudentDataOfGroup(int groupid){
       
        Connection con = DBConnection.getConnection();
        List<Student> studentList = new ArrayList<>();
        if(con!=null){
            try{
                String query = "SELECT * FROM daycaredb.Student where groupid=? ;";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, groupid);
                
                ResultSet rs =stmt.executeQuery();
                
                studentList = arrangeStudentData(rs);
                
            }catch(SQLException e){
                
            }
            
        }
        return studentList;
    }
    public static List<Student> arrangeStudentData(ResultSet rs){
        List<Student> studentList = new ArrayList<>();
        try{
//            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                Student s = new Student(rs.getInt("studentId"),rs.getInt("age"),rs.getString("address"),rs.getString("phonenumber")
                        ,rs.getString("registrationdate"), rs.getString("firstname"),rs.getString("lastname"),
                        rs.getString("gender"),rs.getInt("caretakerId"),rs.getInt("groupid"));
                studentList.add(s);
            }
            
            
        }catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        return studentList;
    }
    
}
