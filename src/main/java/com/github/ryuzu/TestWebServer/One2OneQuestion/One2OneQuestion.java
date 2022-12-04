package com.github.ryuzu.TestWebServer.One2OneQuestion;

import com.github.ryuzu.TestWebServer.Utilities.DataBaseUtilities;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class One2OneQuestion {
    @RequestMapping("/api/subjects/export")
    public static String put() {
        Gson gson = new Gson();
        List<HashMap<String, String>> table = DataBaseUtilities.table("subjects" , Arrays.asList("id", "date", "subject", "content"));
        return gson.toJson(table);
    }
}
