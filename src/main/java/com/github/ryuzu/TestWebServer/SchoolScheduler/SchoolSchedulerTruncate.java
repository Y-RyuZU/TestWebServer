package com.github.ryuzu.TestWebServer.SchoolScheduler;

import com.github.ryuzu.TestWebServer.Utilities.DataBaseUtilities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolSchedulerTruncate {
    @RequestMapping("/api/schoolscheduler/clear")
    public static String put() {
        DataBaseUtilities.truncate("subjects");
        return "Clear!";
    }
}
