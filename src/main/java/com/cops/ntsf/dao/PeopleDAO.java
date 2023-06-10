package com.cops.ntsf.dao;

import com.cops.ntsf.model.People;
import com.cops.ntsf.model.User;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleDAO {
    public void fetchCivilInfo(People people) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM people WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, people.getNic());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                people.setNic(resultSet.getString("nic"));
                people.setIssueDate(resultSet.getDate("issue_date"));
                people.setName(resultSet.getString("name"));
                people.setAddress(resultSet.getString("address"));
                people.setDob(resultSet.getDate("dob"));
                people.setGender(resultSet.getString("gender"));
                people.setBirthPlace(resultSet.getString("birth_place"));
                people.setJob(resultSet.getString("job"));
                people.setProfilePicture(resultSet.getBlob("profile_picture"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUser_IDasNIC(String nic) {
        Connection dbConn = null;

        boolean alert = false;

        try {
            dbConn = Database.getConnection();
            String sql = "SELECT nic FROM people WHERE nic = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, nic);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                alert = true;
            } else {
                System.out.println("New Entry!!");
                alert = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alert;
    }

}
