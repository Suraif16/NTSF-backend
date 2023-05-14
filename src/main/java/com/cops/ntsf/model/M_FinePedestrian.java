package com.cops.ntsf.model;

//import com.cops.ntsf.dao.FineDAO;

import com.cops.ntsf.dao.M_FinePedestrianDAO;

import java.time.LocalDateTime;

public class M_FinePedestrian {


    private String licenceNo;

    private String fineNo;
    private String fineType;
    private String descripton;

    private String location;

    private  String policeId;

    private String nic;

    private String vehicleNo;

    LocalDateTime imposedDateTime = LocalDateTime.now();
    LocalDateTime dueDateTime = imposedDateTime.plusDays(14);






    public LocalDateTime getImposedDateTime(){return imposedDateTime;}

    public LocalDateTime getDueDateTime(){return dueDateTime;}




    public M_FinePedestrian(String nic, String location, String fineNo, String description, String policeId, String fineType) {
        this.nic = nic;
        this.location = location;
        this.fineNo = fineNo;
        this.descripton = description;
        this.policeId = policeId;
        this.fineType = fineType;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public String getNic() {
        return nic;
    }

    public String getFineType() {
        return fineType;
    }



    public String getLocation() {
        return location;
    }

    public String getFineNo() {
        return fineNo;
    }

    public String getDescripton() {
        return descripton;
    }

    public String getPoliceId() {
        return policeId;
    }

    public String getVehicleNo(){
        return vehicleNo;
    }
    public Integer setFineInfo() {
        M_FinePedestrianDAO finePedestrianDAO=new M_FinePedestrianDAO();
        return finePedestrianDAO.setFineInfo(this);
    }

}