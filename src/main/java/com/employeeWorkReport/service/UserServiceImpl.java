package com.employeeWorkReport.service;

import com.employeeWorkReport.entity.User;
import com.employeeWorkReport.repository.UserRepository;
import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalTime;
import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(null);
        System.out.println(user.getManager_id());
        return user;
    }

    @Override
    public User updateUser(Integer id, User user) {
        // find the old user
        User prevUser = getUserById(id);

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));

        prevUser.setFirstName(user.getFirstName());
        prevUser.setLastName(user.getLastName());
        prevUser.setEmailId(user.getEmailId());
        prevUser.setPassword(hashedPassword);
        prevUser.setWorkHourStart(user.getWorkHourStart());
        prevUser.setWorkHourEnd(user.getWorkHourEnd());
        // prevUser is now the updated user
        return userRepository.update(prevUser);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User validateLogin(String emailId, String password) throws Exception {
        try {
            User user;
            try {
                user = userRepository.findByEmailId(emailId);
            } catch (Exception e) {
                throw new Exception("Email Id does not exist!!");
            }

            // if passwords do not match ,throw error
            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new Exception("Wrong password!!");
            }

            return getUserById(user.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void makeManger(Integer user_id) {
        try {
            userRepository.updateManagerId(user_id, user_id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void assignManager(Integer user_id, Integer manager_id) {
        try {
            System.out.println(user_id + " " + manager_id);
            userRepository.updateManagerId(user_id, manager_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void removeManager(Integer user_id) {
        try {
            userRepository.setManagerIdToNull(user_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> getUsersWithNoManager() {
        try {
            return userRepository.getUsersWithNoManager();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> getMembersByManagerId(Integer manager_id) {
        try {
            return userRepository.getUsersByManagerId(manager_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
