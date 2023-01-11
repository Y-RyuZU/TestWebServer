package com.github.ryuzu.TestWebServer.One2OneQuestion;

import com.github.ryuzu.TestWebServer.utilities.DataBaseUtilities;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;

@RestController
public class One2OneQuestionInport {
    @RequestMapping("/api/subjects/import")
    public static String get() {
        LinkedHashMap<String , String> values = new LinkedHashMap<>();
        values.put("id", "BIGINT NOT NULL");
        values.put("date", "BIGINT NOT NULL");
        values.put("subject", "VARCHAR(100)");
        values.put("content", "LONG VARCHAR");
        if(!DataBaseUtilities.existTable("subjects")) DataBaseUtilities.createTable("subjects", values);
        DataBaseUtilities.insert("subjects" , Arrays.asList("0", "100000", "'TEST'", "'HOGE'"));
        return "Input!";
    }
}
