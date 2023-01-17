package com.github.ryuzu.TestWebServer.file.operation.controller.download;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
public class FileDownloadController {
    private final FileDownloadService service;

    @GetMapping("api/files/download/{path:.*}")
    public void download(@PathVariable String path, HttpServletResponse response) {
        var file = new File(path);
        if (!file.exists()) throw new IllegalArgumentException();
        service.download(file, response);
    }
}
