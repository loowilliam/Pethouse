package com.example.project.pethouse.model;

/*
    Created By William Loo
    Date : 8 Mei 2020
    Purpose : Buat model user
*/

/*
    Edited By William Loo
    Date : 8 Mei 2020
    Purpose : Gabungin table msuser dengan mspet
*/

/*
    Edited By William Loo
    Date : 8 Mei 2020
    Purpose : pisah tabel msuser dan mspet
*/

public class MsUser {

    private int UserID;
    private String UserName;
    private String Email;
    private String Phone;
    private String Password;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
