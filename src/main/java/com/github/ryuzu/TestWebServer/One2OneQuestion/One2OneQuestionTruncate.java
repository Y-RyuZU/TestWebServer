package com.github.ryuzu.TestWebServer.One2OneQuestion;

import com.github.ryuzu.TestWebServer.Utilities.DataBaseUtilities;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class One2OneQuestionTruncate {
    @RequestMapping("/api/subjects/clear")
    public static String put() {
        DataBaseUtilities.truncate("subjects");
        return "Clear!";
    }
}
