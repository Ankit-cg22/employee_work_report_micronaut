package com.employeeWorkReport.service;

import com.employeeWorkReport.entity.Activity;
import com.employeeWorkReport.entity.CategoryReport;
import com.employeeWorkReport.repository.ActivityRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Map;

@Singleton
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public List<CategoryReport> getActivitySummaryForUserIdsAndDateRange(List<Integer> userIdList, String startDate,
            String endDate) {
        try {
            return activityRepository.getActivitySummaryForUserIdsAndDateRange(userIdList, startDate, endDate);
        } catch (Exception e) {
            System.out.println("issue in repo");
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Activity> getActivitySummaryForTheDay(Integer userId, String date) {
        try {
            return activityRepository.getActivitySummaryForTheDay(userId, date);
        } catch (Exception e) {
            throw e;
        }
    }
}
