package com.demo.demo.model;

public class Users {
    private int userID;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String userRole;

    public Users() {
    }

    public Users(int userID, String username, String password, String fullName, String email, String userRole) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.userRole = userRole;
    }

    public Users(String username, String password, String fullName, String email, String userRole) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.userRole = userRole;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
