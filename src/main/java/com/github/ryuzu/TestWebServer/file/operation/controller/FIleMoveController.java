package com.github.ryuzu.TestWebServer.file.operation.controller;

import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLog;
import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLogType;
import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
import com.google.common.io.Files;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class FIleMoveController {
    @PostMapping("api/files/move/{path:.*}")
    public void move(@AuthenticationPrincipal AccountDetails details, @PathVariable String path, @RequestParam String to) throws IOException {
        var file = new File(path);
        Files.move(file, new File(to));
        FileOperationLog.builder().account(details.getUser())
                .path(path)
                .type(FileOperationLogType.MOVE)
                .detail(to)
                .build().carve();
    }
}
