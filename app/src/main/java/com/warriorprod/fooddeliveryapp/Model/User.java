package com.warriorprod.fooddeliveryapp.Model;

public class User {

    private String Name;
    private String Password;
    private String Phone;
    private String isStaff;

    public User() {
    }

    public User(String name, String password) {
        this.Name = name;
        this.Password = password;
        isStaff = "false";

    }

    public String getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(String isStaff) {
        this.isStaff = isStaff;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
