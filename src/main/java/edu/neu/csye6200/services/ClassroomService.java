/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.services;

import edu.neu.csye6200.controller.DBConnection;
import edu.neu.csye6200.objects.AgeGroupEnum;
import edu.neu.csye6200.objects.CareTaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import edu.neu.csye6200.objects.*;

import edu.neu.csye6200.objects.ClassRoom;

/**
 *
 * @author jasonpauldarivemula
 */
public class ClassroomService {

    public static int insertClassroom(ClassRoom cr) {
        Connection con = DBConnection.getConnection();
        int classroomId = -1;
        if (con != null) {
            try {
                String query = "insert into classroom (grpcapacity,agegroupId,sectionName) values (?,?,?)";
                PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, cr.getGroupAvailableCapacity());
                stmt.setInt(2, cr.getAgeGroup().getAgeGroupId());
                stmt.setString(3, cr.getSectionName());

                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();

                rs.first();
                classroomId = rs.getInt(1);

                stmt.close();
                return classroomId;

            } catch (SQLException ex) {
                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }

        }
        return -1;

    }

    public static List<ClassRoom> fetchClassRooms() {
        Connection con = DBConnection.getConnection();
        return fetchClassRooms(con);
    }

    public static List<ClassRoom> fetchClassRooms(Connection con) {
        List<ClassRoom> classroomList = new ArrayList<>();
        if (con != null) {

            String query = "SELECT * FROM daycaredb.classroom;";
            ResultSet rs = FetchData.SelectQuery(con, query);
            classroomList = arrangeClassRoomData(rs);

//                ResultSetMetaData rsmd = rs.getMetaData();
        }
        return classroomList;
    }

    public static ClassRoom fetchClassRoomUsingId(int classroomId) {
        ClassRoom cr = null;
        Connection con = DBConnection.getConnection();
        if (con != null) {

            try {
                String query = "SELECT * FROM daycaredb.classroom where id=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, classroomId);
                ResultSet rs = stmt.executeQuery();

                int cnt = 0;

                while (rs.next() && cnt == 0) {

                    int classRoomId = rs.getInt("id");
                    AgeGroupEnum ageGroup = AgeGroupEnum.getAgeGroupEnum(rs.getInt("agegroupid"));
                    int capacity = ageGroup.getMaxGroupsPerRoom();
                    String sectionName = rs.getString("sectionName");
                    List<Group> groups = AgeGroupService.getGroupListForClassRoom(classRoomId);
                    List<Teacher> teacherList = new ArrayList<>();
                    List<Student> studentsList = new ArrayList<>();
                    for (Group group : groups) {
                        teacherList.add(TeacherService.getTeacherFromTeacherId(group.getTeacherId()));
                        studentsList.addAll(StudentService.fetchStudentDataOfGroup(group.getGroupId()));
                    }
                    int groupAvailableCapacity = rs.getInt("grpcapacity");
                    cr = new ClassRoom(classRoomId, capacity, ageGroup, studentsList, teacherList, groups, sectionName, groupAvailableCapacity);
                    cnt++;
                }
            } catch (SQLException e) {
            }

//                ResultSetMetaData rsmd = rs.getMetaData();
        }
        return cr;
    }

    public static List<ClassRoom> arrangeClassRoomData(ResultSet rs) {
        List<ClassRoom> classroomList = new ArrayList<>();
        try {
//            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                int classRoomId = rs.getInt("id");
                AgeGroupEnum ageGroup = AgeGroupEnum.getAgeGroupEnum(rs.getInt("agegroupid"));
                int capacity = ageGroup.getMaxGroupsPerRoom();
                String sectionName = rs.getString("sectionName");
                List<Group> groups = AgeGroupService.getGroupListForClassRoom(classRoomId);
                List<Teacher> teacherList = new ArrayList<>();
                List<Student> studentsList = new ArrayList<>();
                for (Group group : groups) {
                    teacherList.add(TeacherService.getTeacherFromTeacherId(group.getTeacherId()));
                    studentsList.addAll(StudentService.fetchStudentDataOfGroup(group.getGroupId()));
                }
                int groupAvailableCapacity = rs.getInt("grpcapacity");
                ClassRoom s = new ClassRoom(classRoomId, capacity, ageGroup, studentsList, teacherList, groups, sectionName, groupAvailableCapacity);
                classroomList.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return classroomList;
    }
}
