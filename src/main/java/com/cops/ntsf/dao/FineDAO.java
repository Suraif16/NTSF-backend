package com.cops.ntsf.dao;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class FineDAO {
    public void createFine(Fine fine) {
        Connection dbConn = null;
        try {
            System.out.println("Reached FineDAO");
            dbConn = Database.getConnection();
            String sql = "INSERT INTO fine (fine_type, offence_no, nic, license_no, vehicle_no, driven_vehicle_no,spot_description, imposed_date_time, due_date_time, police_id, police_station_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, fine.getFineType());
            preparedStatement.setString(2, fine.getOffenceNo());
            preparedStatement.setString(3, fine.getNic());
            preparedStatement.setString(4, fine.getLicenseNo());
            preparedStatement.setString(5, fine.getVehicleNo());
            preparedStatement.setString(6, fine.getDrivenVehicleNo());
            preparedStatement.setString(7, fine.getSpotDescription());
            preparedStatement.setObject(8, fine.getImposedDateTime());
            preparedStatement.setObject(9, fine.getDueDateTime());
            preparedStatement.setString(10, fine.getPoliceId());
            preparedStatement.setString(11, fine.getPoliceStation());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray viewFineDetailsAsOIC() {
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();
            String sql = "SELECT * FROM fine where police_station_name = ? and payment_status = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fine_type", resultSet.getString("fine_type"));
            jsonObject.put("offence_no", resultSet.getString("offence_no"));
            jsonObject.put("nic", resultSet.getString("nic"));
            jsonObject.put("license_no", resultSet.getString("license_no"));
            jsonObject.put("vehicle_no", resultSet.getString("vehicle_no"));
            jsonObject.put("driven_vehicle_no", resultSet.getString("driven_vehicle_no"));
            jsonObject.put("spot_description", resultSet.getString("spot_description"));
            jsonObject.put("imposed_date_time", resultSet.getString("imposed_date_time"));
            jsonObject.put("due_date_time", resultSet.getString("due_date_time"));
            jsonObject.put("police_id", resultSet.getString("police_id"));
            jsonObject.put("police_station_name", resultSet.getString("police_station_name"));
            jsonObject.put("status", resultSet.getString("status"));

            jsonArray.put(jsonObject);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // View fines on user side code starts here
    public ArrayList<Fine> fetchUserFinesInfo(Fine fine) throws SQLException {

        Connection dbConn = Database.getConnection();

        // Get offence_type from offence_no
        String sql = "SELECT * FROM fine WHERE nic = ?";

        PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

        preparedStatement.setString(1, fine.getNic());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Fine> finesList = new ArrayList<Fine>();

        while (resultSet.next()) {
            Fine nextFine;
            nextFine = new Fine(fine.getNic());
            nextFine.setFineNo(resultSet.getInt("fine_no"));
            nextFine.setOffenceNo(resultSet.getString("offence_no"));
//            nextFine.setImposedDateTime(resultSet.getDate("imposed_date_time"));
//            nextFine.setDueDateTime(resultSet.getLocalDateTime("due_date_time"));
            nextFine.setPaymentStatus(resultSet.getString("payment_status"));

            finesList.add(nextFine);
        }
        return finesList;
    }
    // Ends here
}


