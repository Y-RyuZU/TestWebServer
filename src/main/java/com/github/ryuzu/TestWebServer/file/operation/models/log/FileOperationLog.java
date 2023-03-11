package com.github.ryuzu.TestWebServer.file.operation.models.log;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class FileOperationLog {
    Member account;
    String path;
    FileOperationLogType type;
    String detail;
    @Builder.Default
    LocalDateTime time = LocalDateTime.now();


    public void carve() {

    }
}
