package com.cops.ntsf.model;

import com.cops.ntsf.dao.UserDAO;

public class User {
    private String userId;
    private String nic;
    private String email;
    private String mobileNo;

    public User(String nic, String email, String mobileNo) {
        this.nic = nic;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public User(String nic) {
        this.nic = nic;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserInfo() {
        UserDAO userDAO = new UserDAO();
        userDAO.insertUserInfo(this);
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void getUserInfo() {
        UserDAO userDAO = new UserDAO();
        userDAO.fetchUserInfo(this);
    }

    public Integer getUserIdFromNic(String nic) {
        UserDAO userDAO = new UserDAO();
        return userDAO.fetchUserIdFromNic(this);
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


}