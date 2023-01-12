package com.github.ryuzu.TestWebServer.FileOperation.controller;

import com.github.ryuzu.TestWebServer.FileOperation.models.FileInformationEntity;
import com.github.ryuzu.TestWebServer.Main;
import com.github.ryuzu.TestWebServer.utilities.FileOperationUtilities;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
public class FileInformationController {
    @GetMapping("/api/files")
    public String getFiles() {
        String workingDir = "C:\\Users\\atsuk\\Desktop\\IntelliJ IDEA\\TestWebServer";

        File file1 = new File(workingDir);
        File[] list = file1.listFiles();

        List<FileInformationEntity> entities = Arrays.stream(list)
                .map(FileInformationEntity::new).toList();

        return Main.gson.toJson(entities);
    }
}
