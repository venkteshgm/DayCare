package edu.neu.csye6200.objects;

import java.util.List;

public class CareTaker extends Person {

    private List<Student> childList;
    private String address;
    private String phone;
    private int careTakerId;

    public CareTaker(String address, String phone, String firstName, String lastName, String gender) {
        super(firstName, lastName, gender);
        this.address = address;
        this.phone = phone;
    }

    public CareTaker(String address, String phone, int careTakerId, String firstName, String lastName, String gender) {
        super(firstName, lastName, gender);
        this.address = address;
        this.phone = phone;
        this.careTakerId = careTakerId;
    }
    
    
    
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getChildList() {
        return childList;
    }

    public void setChildList(List<Student> childList) {
        this.childList = childList;
    }

}
