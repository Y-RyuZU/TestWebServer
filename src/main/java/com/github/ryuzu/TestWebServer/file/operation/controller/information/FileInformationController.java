package com.github.ryuzu.TestWebServer.file.operation.controller.information;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequiredArgsConstructor
public class FileInformationController {
    private final FileInformationService service;
    public static final String workingDir = "C:\\Users\\atsuk\\Desktop\\IntelliJ IDEA\\TestWebServer";

    @GetMapping("/api/files/get")
    @CrossOrigin("http://localhost:5173")
    public String getFiles() {
        return service.getFiles(new File(System.getProperty("user.dir") + "/" + "src/main"));
    }
}
