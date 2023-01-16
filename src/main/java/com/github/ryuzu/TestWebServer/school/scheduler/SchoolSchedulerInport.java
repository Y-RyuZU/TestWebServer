package com.github.ryuzu.TestWebServer.school.scheduler;

import com.github.ryuzu.TestWebServer.utilities.DataBaseUtilities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;

@RestController
public class SchoolSchedulerInport {
    @RequestMapping("/api/schoolscheduler/import")
    public static String put() {
        LinkedHashMap<String , String> values = new LinkedHashMap<>();
        values.put("day", "VARCHAR(100) NOT NULL");
        values.put("date", "BIGINT NOT NULL");
        values.put("subject", "VARCHAR(100) NOT NULL");
        values.put("class_index", "INT NOT NULL");
        values.put("contents", "LONG VARCHAR");
        String table = "school_schedule";
        if(!DataBaseUtilities.existTable(table)) DataBaseUtilities.createTable(table, values);
        DataBaseUtilities.insert(table , Arrays.asList("'Sunday'", "100000", "'Math'", "1", "'MathContent'"));
        DataBaseUtilities.insert(table , Arrays.asList("'Friday'", "200000", "'Japanese'", "5", "'Japanese'"));
        return "Input!";
    }
}
