package com.cops.ntsf.controller;

import com.cops.ntsf.model.Complaint;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplaintServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("createComplaint")) {
            createComplaint(request, response);
        }
    }

    protected void createComplaint(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

//        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String user_id = request.getParameter("user_id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String complaint_no = request.getParameter("complaint_no");

        //remove later
        System.out.println("Works until Servlet");
        System.out.println(user_id);
        System.out.println(title);
        System.out.println(description);
        System.out.println(complaint_no);

        Complaint complaint = new Complaint(user_id, title, description, complaint_no);
        complaint.complaintAdded();

        out.write(jsonObject.toString());
        out.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
        String complaint_no = req.getParameter("complaint_no");
        ArrayList<Complaint> complaintList;

//        UserService userService = new UserService();
        Complaint complaint = new Complaint(complaint_no);
        try {
            complaintList = complaint.getUserComplaintInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(complaintList));
        out.close();
    }
}
