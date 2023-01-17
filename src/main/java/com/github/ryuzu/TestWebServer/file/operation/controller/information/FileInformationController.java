package com.github.ryuzu.TestWebServer.file.operation.controller.information;

import com.github.ryuzu.TestWebServer.file.operation.models.FileInformationEntity;
import com.github.ryuzu.TestWebServer.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileInformationController {
    private final FileInformationService service;
    public static final String workingDir = "C:\\Users\\atsuk\\Desktop\\IntelliJ IDEA\\TestWebServer";

    @GetMapping("/api/files/get")
    public String getFiles() {
        return service.getFiles(new File(workingDir));
    }
}
