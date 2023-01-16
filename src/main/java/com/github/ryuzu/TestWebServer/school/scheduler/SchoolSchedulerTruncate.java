package com.github.ryuzu.TestWebServer.school.scheduler;

import com.github.ryuzu.TestWebServer.utilities.DataBaseUtilities;
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
