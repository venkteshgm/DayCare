/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.services;

import edu.neu.csye6200.controller.DBConnection;
import edu.neu.csye6200.objects.Group;
import edu.neu.csye6200.objects.AgeGroupEnum;
import edu.neu.csye6200.objects.CareTaker;
import edu.neu.csye6200.objects.Student;
import edu.neu.csye6200.objects.Teacher;
import static edu.neu.csye6200.services.StudentService.arrangeStudentData;
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
public class AgeGroupService {

    public static int getAgeGroup(Connection con, int groupNo) {
        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(String
                    .format("Select * from grps where ageGroup =%s and remainingcapacity>0", String.valueOf(groupNo)));
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    public static int groupAvailability(int age){
         //return available groupID for student of a particular age, -1 if no vacant positions available
        Connection con = DBConnection.getConnection();
        return groupAvailability(con, age);
    }
    
    public static int groupAvailability(Connection con, int age){
        //return available groupID for student of a particular age, -1 if no vacant positions available
        AgeGroupEnum ageGroup = AgeGroupEnum.whichAgeGroupForAge(age);
        if(ageGroup == null)
            return -1;
        else{
            return getAgeGroup(con, ageGroup.getAgeGroupId());
        }
             
    }

    public static void insertAgeGroup(int groupNo, int classroomId, int teacherId) {

        Connection con = DBConnection.getConnection();
       
        if(con!=null){
            try {
                
                String query = "insert into grps(ageGroup, classroomId, maxcapacity,remainingcapacity,teacherId) values(?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, groupNo);
                stmt.setInt(2, classroomId);
                stmt.setInt(3, getMaxCapacity(groupNo));
                stmt.setInt(4, getMaxCapacity(groupNo));
                stmt.setInt(5, teacherId);
                
                stmt.executeUpdate();  
                ResultSet rs = stmt.getGeneratedKeys();
                
            } catch (SQLException ex) {
                Logger.getLogger(AgeGroupService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
//    private int getMaxCapacity(int agegroup){
//        if(agegroup == 1 || agegroup == 2 || agegroup == 3 || agegroup== 4)
//            return 3;
//        if(agegroup == 5 || agegroup == 6)
//            return 2;
//        return -1;
//    }
    
    private static int getMaxCapacity(int agegroup){
        for( AgeGroupEnum age: AgeGroupEnum.values()){
            if(age.getAgeGroupId() ==agegroup)
                return age.getMaxGroupSize();
        }
        return -1;
    }

    public static void decrementCapacity(Connection con, int groupNo) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(String.format("UPDATE grps SET remainingcapacity = remainingcapacity - 1 WHERE id = %s",
                    String.valueOf(groupNo)));
            } catch (SQLException ex) {
            Logger.getLogger(AgeGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    
    public static List<Group> getGroupListForClassRoom( int classroomNo){
        Connection con = DBConnection.getConnection();
        if (con != null) {
            try {
                String query = "Select * from grps where classroomId=?";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                //stmt.setString(1,String.valueOf(classroomNo));
                stmt.setInt(1,classroomNo);
                ResultSet rs = stmt.executeQuery();
                 List<Group> list = new ArrayList<Group>();
                while(rs.next()){
                    Group grp = new Group(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getInt(6));
                    list.add(grp);
                }
                  System.out.println(list);
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return new ArrayList<Group>();
            }
        }
        return new ArrayList<Group>();
    }
    
    public static Group fetchGroup(int groupid){
       Connection con = DBConnection.getConnection();
        Group grp = null;
        if(con!=null){
            try{
                String query = "SELECT * FROM daycaredb.grps where id=? ;";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, groupid);
                
                ResultSet rs =stmt.executeQuery();
                 int cnt = 0;

                while (rs.next() && cnt == 0) {
                     grp = new Group(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getInt(6));
                    cnt++;
                }
                
            }catch(SQLException e){
                
            }
            
        }
        return grp;
    }

}
