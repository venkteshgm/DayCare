package edu.neu.csye6200.objects;

import java.util.List;
import edu.neu.csye6200.objects.AgeGroupEnum;

public class ClassRoom {
    private int classRoomId;
    private int capacity;
    private AgeGroupEnum ageGroup;
    private List<Student> studentsList;
    private List<Teacher> teacherList;
    private List<Group> groups;
    private String sectionName;
//    private int agegroupId;
    private int groupAvailableCapacity;

    public ClassRoom(int agegroupId, int groupAvailableCapacity, String sectionName) {
        this.ageGroup = AgeGroupEnum.getAgeGroupEnum(agegroupId);
        this.groupAvailableCapacity = groupAvailableCapacity;
        this.sectionName = sectionName;
    }
    public ClassRoom(int classRoomId,int capacity,AgeGroupEnum ageGroup,List<Student> studentsList,List<Teacher> teacherList,
            List<Group> groups,String sectionName,int groupAvailableCapacity){
        this.classRoomId = classRoomId;
        this.capacity = capacity;
        this.ageGroup = ageGroup;
        this.studentsList = studentsList;
        this.teacherList = teacherList;
        this.groups = groups;
        this.sectionName = sectionName;
        this.groupAvailableCapacity = groupAvailableCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public AgeGroupEnum getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroupEnum ageGroup) {
        this.ageGroup = ageGroup;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

//    public int getAgegroupId() {
//        return agegroupId;
//    }
//
//    public void setAgegroupId(int agegroupId) {
//        this.agegroupId = agegroupId;
//    }

    public int getGroupAvailableCapacity() {
        return groupAvailableCapacity;
    }

    public void setGroupAvailableCapacity(int groupAvailableCapacity) {
        this.groupAvailableCapacity = groupAvailableCapacity;
    }
    public int getClassroomId() {
        return classRoomId ;
    }
    

}
