package com.employeeWorkReport.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Constants {
    public static String BASE_SERVER_URL = "http://localhost:8080";
    public static String BASE_FRONTEND_URL = "http://localhost:3000";

    public static Date stringToDate(String dateStr){
        LocalDate localDate = LocalDate.parse(dateStr);
        Date date = Date.valueOf(localDate);
        return date;
    }
    public static String dateToString(Date sqlDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(sqlDate);
    }
}
