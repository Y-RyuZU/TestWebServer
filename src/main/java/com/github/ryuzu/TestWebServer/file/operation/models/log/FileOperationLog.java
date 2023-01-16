package com.github.ryuzu.TestWebServer.file.operation.models.log;

import com.github.ryuzu.TestWebServer.security.entity.AccountEntity;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public class FileOperationLog {
    AccountEntity account;
    String path;
    FileOperationLogType type;
    String detail;
    @Builder.Default
    LocalDateTime time = LocalDateTime.now();


    public void carve() {

    }
}
