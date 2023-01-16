package com.github.ryuzu.TestWebServer.file.operation.controller;

import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLog;
import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLogType;
import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class FileRemoveController {
    @PostMapping("api/files/remove/{path:.*}")
    public void remove(@AuthenticationPrincipal AccountDetails details, @PathVariable String path) {
        var file = new File(path);
        file.deleteOnExit();
        FileOperationLog.builder().account(details.getUser())
                .path(path)
                .type(FileOperationLogType.REMOVE)
                .build().carve();
    }
}
