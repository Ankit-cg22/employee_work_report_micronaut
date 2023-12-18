package com.employeeWorkReport.controller;

import com.employeeWorkReport.entity.Activity;
import com.employeeWorkReport.entity.Form;
import com.employeeWorkReport.service.ActivityService;
import com.employeeWorkReport.service.FormService;
import com.employeeWorkReport.utils.Constants;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/api/form")
public class FormController {

    private final FormService formService;
    private final ActivityService activityService;

    public FormController(FormService formService, ActivityService activityService) {
        this.formService = formService;
        this.activityService = activityService;
    }

    @Post("/createForm")
    public HttpResponse<Map<String, Object>> createFormEntry(@Body Map<String, Object> requestBody) {
        Integer userId = (Integer) requestBody.get("userId");
        Date date = Constants.stringToDate((String) requestBody.get("date"));
        Form form = formService.createForm(userId, date);
        return HttpResponse.created(formObjectToMap(form));
    }

    @Get("/getForm/{formId}")
    public HttpResponse<Map<String, Object>> getFormById(@PathVariable("formId") Integer formId) {
        System.out.println(formId);
        try {
            Form form = formService.getFormById(formId);
            return HttpResponse.ok(formObjectToMap(form));
        } catch (RuntimeException e) {
            Map<String, Object> ret = new HashMap<>();
            ret.put("error", "form not found");
            return HttpResponse.notFound(ret);
        }
    }

    // @CrossOrigin("http://localhost:3000")
    @CrossOrigin({ "http://localhost:3000", "http://localhost:3001",
            "http://localhost:3002", "https://employee-work-report-frontend.vercel.app" })
    @Post("/submitForm/{formId}")
    public HttpResponse<Map<String, Object>> submitForm(@PathVariable("formId") Integer formId,
            @Body Map<String, Object> requestBody) {

        Map<String, Object> ret = new HashMap<>();

        try {

            System.out.println("getting user id  from frontend");
            // userId of the user who is submitting the form
            Integer userId = (Integer) requestBody.get("userId");
            System.out.println("user id is " + userId);

            System.out.println("getting original userid");

            // original userId for which form was created
            Form form = formService.getFormById(formId);
            Integer originalUserId = form.getUser_id();

            System.out.println("original user id is " + originalUserId);

            if (userId != originalUserId) {
                System.out.println("wrong user");
                ret.put("msg", "Unauthorized access!");
                return HttpResponse.badRequest(ret);
            }

            if (form.getSubmited() == true) {
                System.out.println("already sbmitted user");
                ret.put("msg", "Form is already submitted before.");
                return HttpResponse.badRequest(ret);
            }

            List<Map<String, Object>> activityList = (List<Map<String, Object>>) requestBody.get("activityList");
            System.out.println("now pushing the values to activity table");
            for (Map<String, Object> map : activityList) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                }
                Activity activity = new Activity(
                        (String) map.get("work_category"),
                        (Integer) map.get("hours"),
                        (Integer) map.get("minutes"),
                        (String) map.get("description"),
                        formId);
                System.out.println(activity.getForm_id());
                System.out.println(activity.getId());
                System.out.println(activity.getHours());
                System.out.println(activity.getMinutes());
                System.out.println(activity.getWork_category());
                activityService.createActivity(activity);
            }

            System.out.println("values pushed");

            form.setSubmitted(true);

            System.out.println("now marking form as submitted");
            formService.updateForm(formId, form);
            ret.put("msg", "Form submitted successfully.");

            return HttpResponse.created(ret);
        } catch (RuntimeException e) {
            ret.put("msg", e.getMessage());
            return HttpResponse.badRequest(ret);
        }
    }

    // @CrossOrigin("http://localhost:3000")
    @CrossOrigin({ "http://localhost:3000", "http://localhost:3001",
            "http://localhost:3002", "https://employee-work-report-frontend.vercel.app" })
    @Get("/unsubmitteForms/{user_id}")
    public HttpResponse<Map<String, Object>> getUnsubmittedFormsByUserId(Integer user_id) {
        System.out.println("in controller");
        Map<String, Object> map = new HashMap<>();
        try {
            List<Form> unsubmittedFormList = formService.getUnsubmittedFormsByUserId(user_id);
            List<Map<String, Object>> ret = new ArrayList<>();
            for (Form f : unsubmittedFormList) {
                ret.add(formObjectToMap(f));
            }
            map.put("data", ret);
            return HttpResponse.ok(map);
        } catch (Exception e) {
            map.put("error", "Internal Server Error");
            return HttpResponse.serverError(map);
        }
    }

    private Map<String, Object> formObjectToMap(Form form) {
        Map<String, Object> map = new HashMap<>();
        map.put("formId", form.getId());
        map.put("userId", form.getUser_id());
        map.put("date", Constants.dateToString(form.getDate()));
        return map;
    }
}
