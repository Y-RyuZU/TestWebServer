package com.github.ryuzu.TestWebServer.file.operation.controller.move;

import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLog;
import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLogType;
import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FIleMoveController {
    private final FIleMoveService service;
    @PostMapping("api/files/move/{path:.*}")
    public void move(@AuthenticationPrincipal AccountDetails details, @PathVariable String path, @RequestParam String to) throws IOException {
        var fromFolder = new File(path);
        var toFolder = new File(path);
        service.move(details, fromFolder , toFolder);
    }
}
