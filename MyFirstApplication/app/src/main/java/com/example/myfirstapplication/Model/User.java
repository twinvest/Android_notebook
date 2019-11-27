package com.example.myfirstapplication.Model;

public class User {
    private String Id, Name, Password, Roomsize, Floor, Managecost, Specialmemo;
    private String Roomtype,Heatingtype, Elevator,Balcony, Loan;


    private User(){}
    public User(String id, String name, String password, String roomsize, String floor, String managecost,
                String specialmemo, String roomtype, String heatingtype, String elevator, String balcony, String loan) {
        this.Id = id;
        this.Name = name;
        this.Password = password;
        this.Roomsize = roomsize;
        this.Floor = floor;
        this.Managecost = managecost;
        this.Specialmemo = specialmemo;
        this.Roomtype = roomtype;
        this.Heatingtype = heatingtype;
        this.Elevator = elevator;
        this.Balcony = balcony;
        this.Loan = loan;
    }

    //게터
    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public String getRoomsize() {
        return Roomsize;
    }

    public String getFloor() {
        return Floor;
    }

    public String getManagecost() {
        return Managecost;
    }

    public String getSpecialmemo() {
        return Specialmemo;
    }

    public String getRoomtype() {
        return Roomtype;
    }

    public String getHeatingtype() {
        return Heatingtype;
    }

    public String getElevator() {
        return Elevator;
    }

    public String getBalcony() {
        return Balcony;
    }

    public String getLoan() {
        return Loan;
    }

    //세터
    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRoomsize(String roomsize) {
        Roomsize = roomsize;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public void setManagecost(String managecost) {
        Managecost = managecost;
    }

    public void setSpecialmemo(String specialmemo) {
        Specialmemo = specialmemo;
    }

    public void setRoomtype(String roomtype) {
        Roomtype = roomtype;
    }

    public void setHeatingtype(String heatingtype) {
        Heatingtype = heatingtype;
    }

    public void setElevator(String elevator) {
        Elevator = elevator;
    }

    public void setBalcony(String balcony) {
        Balcony = balcony;
    }

    public void setLoan(String loan) {
        Loan = loan;
    }
}
