package com.github.ryuzu.TestWebServer.school.scheduler;

import com.github.ryuzu.TestWebServer.utilities.DataBaseUtilities;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SchoolScheduler {
    @GetMapping("/api/schoolscheduler/export")
    public static String get(@RequestParam("day") String day) {
        Gson gson = new Gson();
        List<HashMap<String, String>> table = DataBaseUtilities.table(
                "school_schedule" ,
                Arrays.asList("class_index", "subject", "contents") ,
                "day = " + DataBaseUtilities.toSQLString(day)
        );
        return gson.toJson(table);
    }
}
