package com.github.ryuzu.TestWebServer.file.operation.controller.download;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileDownloadService {
    public void download(File file, HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        try (var out = response.getOutputStream()) {
            out.write(Files.readAllBytes(Path.of(file.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
