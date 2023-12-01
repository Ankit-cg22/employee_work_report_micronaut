package com.employeeWorkReport.controller;
import com.employeeWorkReport.entity.User;
import com.employeeWorkReport.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin("http://localhost:3000")
    @Post("/register")
    public HttpResponse<User> registerUser(@Body User user) {
        System.out.println("registering user.");
        return HttpResponse.created(userService.registerUser(user));
    }

    @Get("/allUsers")
    public HttpResponse<List<User>> getAllUsers() {
        return HttpResponse.ok(userService.getAllUsers());
    }

    @Get("/getUser/{id}")
    public HttpResponse<User> getUserById(@PathVariable("id") Integer userId) {
        return HttpResponse.ok(userService.getUserById(userId));
    }

    @Put("/updateUser/{id}")
    public HttpResponse<User> updateUser(@PathVariable("id") Integer id, @Body User user) {
        return HttpResponse.ok(userService.updateUser(id, user));
    }

    @Delete("/deleteUser/{id}")
    public HttpResponse<String> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return HttpResponse.ok("User Deleted!!");
    }

    @CrossOrigin("http://localhost:3000")
    @Post("/login")
    public HttpResponse<Map<String, Object>> loginUser(@Body Map<String, Object> map) {
        String email = (String) map.get("emailId");
        String password = (String) map.get("password");
        Map<String, Object> returnObject = new HashMap<>();
        try {
            User user = userService.validateLogin(email, password);
            returnObject.put("user", user);
            return HttpResponse.ok(returnObject);
        } catch (Exception e) {
            returnObject.put("error", e.getMessage());
            return HttpResponse.badRequest(returnObject);
        }

    }

    @Get("/makeManager/{user_id}")
    public HttpResponse<Map<String, Object>> makeManager(@PathVariable("user_id") Integer user_id) {
        Map<String, Object> ret = new HashMap<>();

        try {
            userService.makeManger(user_id);
            ret.put("msg", "User successfully promoted to manager.");
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }

    @Get("/assignManager/{user_id}/{manager_id}")
    public HttpResponse<Map<String, Object>> assignManager(@PathVariable("user_id") Integer user_id, @PathVariable("manager_id") Integer manager_id) {
        Map<String, Object> ret = new HashMap<>();

        try {
            userService.assignManager(user_id, manager_id);
            ret.put("msg", "Manager successfully assigned.");
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }

    @Get("/removeMember/{manager_id}/{user_id}")
    public HttpResponse<Map<String, Object>> removeMember(@PathVariable("manager_id") Integer manager_id, @PathVariable("user_id") Integer user_id) {
        Map<String, Object> ret = new HashMap<>();
        User user = userService.getUserById(user_id);
//        if(user == null){
//            ret.put("msg" , "No such user.");
//            return HttpResponse.badRequest(ret);
//        }
//        System.out.println(user.getId());

        if (user.getManager_id() != manager_id) {
            ret.put("msg", "You are not authorized to perform this task.");
            return HttpResponse.badRequest(ret);
        }
        System.out.println(user.getId());

        try {
            userService.removeManager(user_id);
            ret.put("msg", "Member successfully removed from team");
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }

    @Get("/getUsersWithNoManager")
    public HttpResponse<Map<String, Object>> getUsersWithNoManager() {
        Map<String, Object> ret = new HashMap<>();
        try {
            List<User> userList = userService.getUsersWithNoManager();
            ret.put("data", userList);
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }

    @Get("/getMembersByManagerId/{manager_id}")
    public HttpResponse<Map<String, Object>> getMembersByManagerId(@PathVariable("manager_id") Integer manager_id) {
        Map<String, Object> ret = new HashMap<>();
        try {
            List<User> userList = userService.getMembersByManagerId(manager_id);
            ret.put("data", userList);
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }
}