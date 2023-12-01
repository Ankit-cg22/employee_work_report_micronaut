package com.employeeWorkReport.service;

import com.employeeWorkReport.entity.Activity;
import com.employeeWorkReport.entity.CategoryReport;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ActivityService {
    Activity createActivity(Activity activity);
    List<CategoryReport> getActivitySummaryForUserIdsAndDateRange(List<Integer> userIdList , String startDate , String endDate );
}
