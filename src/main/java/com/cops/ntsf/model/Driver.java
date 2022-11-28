package com.cops.ntsf.model;

import com.cops.ntsf.dao.DriverDAO;

public class Driver extends User{
    private String userId;
    private String licenceNo;

    public Driver(String licenseNo) {
        this.licenceNo = licenseNo;
    }

    public Driver() {

    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public void getDriverFromLicenseNo() {
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.getDriverFromLicense(this);
    }

    public void setDriverInfo(){
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.insertDriverInfo(this);
    }
}
