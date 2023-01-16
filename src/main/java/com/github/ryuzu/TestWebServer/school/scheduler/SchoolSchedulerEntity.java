package com.github.ryuzu.TestWebServer.school.scheduler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SchoolSchedulerEntity {
    public int id;
    public long date;
    public String subject;
    public String content;
}
