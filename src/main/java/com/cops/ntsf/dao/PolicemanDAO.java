package com.cops.ntsf.dao;

import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.util.DBConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class PolicemanDAO {
    public String createPoliceman(Policeman policeman)
    {
        Connection dbConnect = null;
        try {
            dbConnect = DBConnect.getConnection();
            String sql = "INSERT into policeman (name, police_id, nic, rank, police_station) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, policeman.getName());
            preparedStatement.setString(2, policeman.getPolice_id());
            preparedStatement.setString(3, policeman.getNic());
            preparedStatement.setString(4, policeman.getRank());
            preparedStatement.setString(5, policeman.getPolice_station());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (dbConnect != null) try
            {
                dbConnect.close();
            } catch (Exception ignore){}
        }
        return null;
    }

    public JSONArray getPolicemanDetailsList() {
        Connection dbConnect = null;

//        ArrayList<Policeman> policemanDetails = new ArrayList<>(); //not used
        JSONArray jsonArray = new JSONArray();

        try {
            dbConnect = DBConnect.getConnection();

            String sql = "SELECT * from policeman";

            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                String police_id = resultSet.getString("police_id");
                String nic = resultSet.getString("nic");
                String rank = resultSet.getString("rank");
                String police_station = resultSet.getString("police_station");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("police_id", police_id);
                jsonObject.put("nic", nic);
                jsonObject.put("rank", rank);
                jsonObject.put("police_station", police_station);

                jsonArray.put(jsonObject);

                System.out.println(name);
                System.out.println(police_id);
                System.out.println(nic);
                System.out.println(rank);
                System.out.println(police_station);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConnect != null) try {
                dbConnect.close();
            } catch (Exception ignore) {
            }
        }
        return jsonArray;
    }

    public boolean getPolicemanPolice_IDCheckResult(String police_idCheck) {
        Connection dbConnect = null;

        boolean alert = false;
        try {
            dbConnect = DBConnect.getConnection();

            String sql = "SELECT police_id from policeman where police_id =  ?";

            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, police_idCheck);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                alert = true;
            } else {
                System.out.println("New Entry!!");
                alert = false;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConnect != null) try {
                dbConnect.close();
            } catch (Exception ignore) {
            }
        }
        return alert;
    }

    public boolean getPolicemanNicCheckResult(String nicCheck) {
        Connection dbConnect = null;

        boolean alert = false;
        try {
            dbConnect = DBConnect.getConnection();

            String sql = "SELECT nic from policeman where nic =  ?";

            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql);
            preparedStatement.setString(1, nicCheck);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                alert = true;
            } else {
                System.out.println("New Entry!!");
                alert = false;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConnect != null) try {
                dbConnect.close();
            } catch (Exception ignore) {
            }
        }
        return alert;
    }

}



