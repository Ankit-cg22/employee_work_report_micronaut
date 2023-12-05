package com.employeeWorkReport.service;

import com.employeeWorkReport.entity.Activity;
import com.employeeWorkReport.entity.CategoryReport;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.swing.UIDefaults.ActiveValue;

public interface ActivityService {
    Activity createActivity(Activity activity);

    List<CategoryReport> getActivitySummaryForUserIdsAndDateRange(List<Integer> userIdList, String startDate,
            String endDate);

    List<Activity> getActivitySummaryForTheDay(Integer userId, String date);
}
