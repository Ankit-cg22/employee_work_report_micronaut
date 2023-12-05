package com.employeeWorkReport.controller;

import com.employeeWorkReport.entity.Activity;
import com.employeeWorkReport.entity.CategoryReport;
import com.employeeWorkReport.entity.User;
import com.employeeWorkReport.service.ActivityService;
import com.employeeWorkReport.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/api/analytics")
public class AnalyticsController {
    private final ActivityService activityService;
    private final UserService userService;

    public AnalyticsController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    @Post("/getReport")
    public HttpResponse<Map<String, Object>> getReport(@Body Map<String, Object> requestBody) {
        Map<String, Object> ret = new HashMap<>();
        try {
            List<Integer> userIdList = (List<Integer>) requestBody.get("userIdList");
            String startDate = (String) requestBody.get("startDate");
            String endDate = (String) requestBody.get("endDate");

            List<CategoryReport> report = activityService.getActivitySummaryForUserIdsAndDateRange(userIdList,
                    startDate, endDate);

            ret.put("cummulativeReport", report);
            List<Map<String, Object>> individualReports = new ArrayList<>();
            for (Integer id : userIdList) {
                Map<String, Object> map = new HashMap<>();
                List<Integer> list = new ArrayList<>();
                list.add(id);
                List<CategoryReport> indReport = activityService.getActivitySummaryForUserIdsAndDateRange(list,
                        startDate, endDate);
                User user = userService.getUserById(id);

                map.put("user", user);
                map.put("report", indReport);
                individualReports.add(map);
            }
            ret.put("individualReports", individualReports);
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }

    @Post("/dayWiseDetails/{userId}")
    public HttpResponse<Map<String, Object>> getActivitySummaryForTheDay(@PathVariable Integer userId,
            @Body Map<String, Object> requestBody) {
        String date = (String) requestBody.get("date");
        Map<String, Object> ret = new HashMap<>();
        try {
            List<Activity> activityList = activityService.getActivitySummaryForTheDay(userId, date);
            ret.put("data", activityList);
            return HttpResponse.ok(ret);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ret.put("msg", "Internal Server Error");
            return HttpResponse.serverError(ret);
        }
    }
}
