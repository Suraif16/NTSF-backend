package com.cops.ntsf.dao;

import com.cops.ntsf.model.Driver;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDAO {
    /**
     * Function to query driver table with license no
     */
    public void getDriverFromLicense(Driver driver) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM driver WHERE licence_no = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, driver.getLicenceNo());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                driver.setUserId(resultSet.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertDriverInfo(Driver driver) {
        Connection dbConn = Database.getConnection();

        String sql = "UPDATE driver SET user_id=?, licence_no=? WHERE user_id = ?";

        try{
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, driver.getLicenceNo());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
