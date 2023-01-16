package com.github.ryuzu.TestWebServer.file.operation.controller.download;

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
public class FileDownloadController {
    FileDownloadService service;

    public FileDownloadController(FileDownloadService service) {
        this.service = service;
    }

    @GetMapping("api/files/move/{path:.*}")
    public void download(@PathVariable String path, HttpServletResponse response) {
        var file = new File(path);
        if (!file.exists()) throw new IllegalArgumentException();
        service.download(file, response);
    }
}
