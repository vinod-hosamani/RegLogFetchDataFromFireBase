package com.example.mgs1982.remindset.registration.model;


public class UserModel
{
    private String id;
    private String userName;
    private String password;
    private String email;

    public String getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
