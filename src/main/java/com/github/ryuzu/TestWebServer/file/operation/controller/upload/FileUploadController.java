package com.github.ryuzu.TestWebServer.file.operation.controller.upload;

import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLog;
import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLogType;
import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService service;

    @PostMapping("api/files/upload/{path:.*}")
    public void upload(@AuthenticationPrincipal AccountDetails details, @PathVariable String path, @RequestParam("file") MultipartFile file) {
        var folder = new File(path);
        service.upload(details, folder, file);
    }
}