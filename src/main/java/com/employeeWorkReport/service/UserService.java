package com.employeeWorkReport.service;

import com.employeeWorkReport.entity.User;

import java.time.LocalTime;
import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> getAllUsers();
    User getUserById(Integer id);
    User updateUser(Integer id , User user);
    void deleteUser(Integer id);
    User validateLogin(String emailId , String password) throws Exception;
    void makeManger(Integer user_id);
    void assignManager(Integer user_id, Integer manager_id);
    void removeManager(Integer user_id);
    List<User> getUsersWithNoManager();
    List<User> getMembersByManagerId(Integer manager_id);
}
