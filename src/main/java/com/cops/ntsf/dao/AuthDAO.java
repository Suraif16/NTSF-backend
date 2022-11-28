package com.cops.ntsf.dao;

import com.cops.ntsf.model.Auth;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {
    /**
     *
     * Function to query auth table with user id
     */
    public void getAuthFromUserId(Auth auth){
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM auth WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, auth.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                auth.setPassword(resultSet.getString("password"));
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertPassword(Auth auth) {
        Connection dbConn = Database.getConnection();

        String sql = "UPDATE auth SET user_id = ?, password = ? WHERE user_id = ?";

        try{
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, auth.getPassword());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

