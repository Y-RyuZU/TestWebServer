package com.github.ryuzu.TestWebServer.One2OneQuestion;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class One2OneQuestionEntity {
    public int id;
    public long date;
    public String subject;
    public String content;
}
