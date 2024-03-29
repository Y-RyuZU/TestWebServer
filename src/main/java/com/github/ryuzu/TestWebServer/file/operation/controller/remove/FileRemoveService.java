package com.github.ryuzu.TestWebServer.file.operation.controller.remove;

import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLog;
import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLogType;
import com.github.ryuzu.TestWebServer.security.entity.AccountDetails;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileRemoveService {
    public void remove(AccountDetails details, File file) {
        file.deleteOnExit();
        FileOperationLog.builder().account(details.getEntity())
                .path(file.getPath())
                .type(FileOperationLogType.REMOVE)
                .build().carve();
    }
}
