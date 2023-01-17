package com.github.ryuzu.TestWebServer.file.operation.controller.move;

import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLog;
import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLogType;
import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
import com.google.common.io.Files;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@Service
public class FIleMoveService {
    public void move(AccountDetails details, File from, File to) throws IOException {
        Files.move(from, to);
        FileOperationLog.builder().account(details.getUser())
                .path(from.getPath())
                .type(FileOperationLogType.MOVE)
                .detail(to.getPath())
                .build().carve();
    }
}
