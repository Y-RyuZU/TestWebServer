package com.github.ryuzu.TestWebServer.file.operation.controller.information;

import com.github.ryuzu.TestWebServer.Main;
import com.github.ryuzu.TestWebServer.file.operation.models.information.FileInformationEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class FileInformationService {
    public String getFiles(File file) {
        File[] list = file.listFiles();

        List<FileInformationEntity> entities = Arrays.stream(list)
                .map(FileInformationEntity::new).toList();

        return Main.gson.toJson(entities);
    }
}
