package com.cops.ntsf.dao;

import com.cops.ntsf.model.User;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public class UserDAO {
    /**
     * Function to insert data into user table
     */
    public void insertUserInfo(User user) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO user (nic, email, mobile_no) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, user.getNic());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobileNo());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchUserInfo(User user) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM user WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, user.getNic());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(resultSet.getString("user_id"));
                user.setNic(resultSet.getString("nic"));
                user.setEmail(resultSet.getString("email"));
                user.setMobileNo(resultSet.getString("mobile_no"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer fetchUserIdFromNic(User user) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT user_id FROM user WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, user.getNic());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(String.valueOf(resultSet.getInt("user_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Integer.valueOf(user.getUserId());
    }


}
