package edu.neu.csye6200.objects;

import java.util.List;

public class DaySchool {

	private String schoolName;
	private List<Teacher> teacherList;
	private List<Student> studentList;
	private List<ClassRoom> classRoomList;
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public List<Teacher> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public List<ClassRoom> getClassRoomList() {
		return classRoomList;
	}
	public void setClassRoomList(List<ClassRoom> classRoomList) {
		this.classRoomList = classRoomList;
	}
	
}