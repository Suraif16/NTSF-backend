package com.cops.ntsf.dao;

import com.cops.ntsf.constants.PaymentStatus;
import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FineDAO {
    public ArrayList<Fine> fetchUserFinesInfo(Fine fine) throws SQLException {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM fine WHERE user_id = ? ORDER BY fine_type";

        PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

        preparedStatement.setString(1, fine.getUserId());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Fine> finesList = new ArrayList<Fine>();

        while (resultSet.next()) {
            Fine nextFine;
            nextFine = new Fine(fine.getUserId());
            nextFine.setTicketNo(resultSet.getString("ticket_no"));
            nextFine.setFineNo(resultSet.getString("fine_no"));
            nextFine.setDate(resultSet.getDate("date"));
            nextFine.setDueDate(resultSet.getDate("due_date"));
            nextFine.setFineAmount(resultSet.getString("fine_amount"));
            nextFine.setPaymentStatus(PaymentStatus.valueOf(resultSet.getString("payment_status")));

            finesList.add(nextFine);
        }
        return finesList;
    }

    public void insertFineInfo(Fine fine) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO fine (ticket_no, fine_no, date, due_date, fine_amount, payment_status, fine_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, fine.getTicketNo());
            preparedStatement.setString(2, fine.getFineNo());
            preparedStatement.setString(3, String.valueOf(fine.getDate()));
            preparedStatement.setString(4, String.valueOf(fine.getDueDate()));
            preparedStatement.setString(5, fine.getFineAmount());
            preparedStatement.setString(6, String.valueOf(fine.getPaymentStatus()));
            preparedStatement.setString(1, String.valueOf(fine.getFineType()));

            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}