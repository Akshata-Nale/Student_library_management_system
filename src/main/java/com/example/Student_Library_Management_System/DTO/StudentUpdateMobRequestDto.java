package com.example.Student_Library_Management_System.DTO;

public class StudentUpdateMobRequestDto {

    private int id;
    private String mobNo;

    //Dtos depend on the APIs being called...add attributes aas required

    public StudentUpdateMobRequestDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
}




























