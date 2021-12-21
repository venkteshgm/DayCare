/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.services;

import edu.neu.csye6200.controller.DBConnection;
import edu.neu.csye6200.objects.Group;
import edu.neu.csye6200.objects.Immunization;
import edu.neu.csye6200.objects.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jasonpauldarivemula
 */
public class ImmunizationService {
    
    /**
     *
     * @return
     */
    public static List<Immunization> getImmunizationAlerts()  {
        
         List<Immunization> imunizationList = new ArrayList<Immunization>();
        try{
        Connection con = DBConnection.getConnection();
        Calendar calendarEnd=Calendar.getInstance();
//        System.out.println(new Date(calendarEnd));
        System.out.println(calendarEnd.get(Calendar.MONTH));
         // You can substract from the current Year to get the previous year last dates.
         calendarEnd.set(Calendar.YEAR,calendarEnd.get(Calendar.YEAR)-1);

         String endDateStr = calendarEnd.get(Calendar.YEAR)+"-"+calendarEnd.get(Calendar.MONTH)+"-"+calendarEnd.get(Calendar.DAY_OF_MONTH);
         
          System.out.println(endDateStr);
      
       Statement stmt = con.createStatement();
       ResultSet rs =  stmt.executeQuery(String.format("Select * from Immunization where vaccinationdate=\"%s\"",endDateStr));
//       List<String> list = new ArrayList<String>();
       while(rs.next()) {
            imunizationList.add(new Immunization(rs.getString(1), rs.getString(3), rs.getInt(4), rs.getInt(2)));
       }
       System.out.println(imunizationList);
        } catch( SQLException ex){
            System.out.println(ex.getMessage());
            return new ArrayList<Immunization>();
        }
       return imunizationList;
	}

	public static void insertImmunizationRecord(Immunization im ) throws SQLException {
                Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
                String endDateStr = im.getDatesOfVaccination();
                String str = String.format("insert into Immunization values(\"%s\",\"%s\",\"%s\",%s)",
					im.getVaccineName(), String.valueOf(im.getStudentId()),endDateStr,String.valueOf(im.getFrequency()));
                //TODO: Check this logic 
		
			stmt.executeUpdate(str);
		
	}

    public boolean getImmunizationAlerts(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static List<Immunization> getImmunizationOfStudent(int studentID){
        List<Immunization> imunizationList = new ArrayList<Immunization>();
        Connection con = DBConnection.getConnection();
        if (con != null) {
            try {
                String query = "Select * from Immunization where studentId= ?";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                //stmt.setString(1,String.valueOf(classroomNo));
                stmt.setInt(1,studentID);
                ResultSet rs = stmt.executeQuery();
                 List<Immunization> list = new ArrayList<Immunization>();
                while(rs.next()){
                    Immunization im = new Immunization(rs.getString(1),rs.getString(3));
                    list.add(im);
                }
                  System.out.println(list);
                return list;
            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return new ArrayList<Immunization>();
            }
        }
        return new ArrayList<Immunization>();
        
    }
    
}
