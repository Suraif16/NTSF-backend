package com.cops.ntsf.service;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.model.Auth;
import com.cops.ntsf.model.Driver;
import com.cops.ntsf.model.User;

public class UserService {
    public User getUserSignedUp(UserType userType,
                                String nic,
                                String email,
                                String mobileNo,
                                String password,
                                String loginId) {
        User user = new User(nic, email, mobileNo, userType);
        user.setUserInfo();

        String userId = user.getUserId();

        if (userId != null) {
            Auth auth = new Auth(userId, password);
            auth.setAuthInfo();
            switch (userType) {
                case DRIVER:
                    Driver driver = new Driver(userId, loginId);
                    driver.setDriverInfo();
                    return user;
                default:
                    throw new RuntimeException();
            }
        }
        return user;
    }
}
