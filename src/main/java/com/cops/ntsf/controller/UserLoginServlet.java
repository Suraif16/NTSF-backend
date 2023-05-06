package com.cops.ntsf.controller;

import com.cops.ntsf.service.AuthService;
import com.cops.ntsf.util.PasswordHashUtil;
import com.cops.ntsf.util.Validator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Get request parameters
        String nic = req.getParameter("nic");
        String password = req.getParameter("password");

        String hashedPassword;
        try {
            hashedPassword = PasswordHashUtil.hashingPassword(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Validator validator = new Validator();
        int validateStatusCode = validator.validateParams(nic, password);

        switch (validateStatusCode) {
            case 0:
                AuthService authService = new AuthService();

                // Output response
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("utf-8");

                out.write(authService.verifyLogin(nic, hashedPassword));
                out.close();
                break;
            case 1:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect NIC Number");
                break;
            case 2:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Password Format");
                break;
            default:
                break;
        }
    }
}