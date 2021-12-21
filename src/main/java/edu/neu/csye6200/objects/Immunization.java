package edu.neu.csye6200.objects;

import java.sql.Date;
import java.util.List;

public class Immunization {
	private String vaccineName;
	private String dateOfVaccination;
	private int frequency;
        private int studentId;

    public Immunization(String vaccineName, String dateOfVaccination, int frequency, int studentId) {
        this.vaccineName = vaccineName;
        this.dateOfVaccination = dateOfVaccination;
        this.frequency = frequency;
        this.studentId = studentId;
    }
    
    public Immunization(String vaccineName, String dateOfVaccination){
    this.vaccineName = vaccineName;
        this.dateOfVaccination = dateOfVaccination;
    }

    public Immunization() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
	
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getDatesOfVaccination() {
		return dateOfVaccination;
	}
	public void setDatesOfVaccination(String datesOfVaccination) {
		this.dateOfVaccination = datesOfVaccination;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}
