package com.example.cupcake_factory.model;

public class User {
    private  String name, address, username, password;
    private int mobileNumber;

    public User() {
    }

    public User(String name, String address, int mobileNumber, String username, String password) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
