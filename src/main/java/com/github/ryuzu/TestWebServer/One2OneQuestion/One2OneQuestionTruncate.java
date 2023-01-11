package com.github.ryuzu.TestWebServer.One2OneQuestion;

import com.github.ryuzu.TestWebServer.utilities.DataBaseUtilities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class One2OneQuestionTruncate {
    @RequestMapping("/api/subjects/clear")
    public static String put() {
        DataBaseUtilities.truncate("subjects");
        return "Clear!";
    }
}
