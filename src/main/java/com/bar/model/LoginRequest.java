package com.bar.model;

public class LoginRequest {
    private  String  userName=null;
    private  String password=null;

    public LoginRequest(){

    }

    public LoginRequest(String  userName,  String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
