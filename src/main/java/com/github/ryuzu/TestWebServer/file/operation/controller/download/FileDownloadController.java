package com.github.ryuzu.TestWebServer.file.operation.controller.download;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
public class FileDownloadController {
    private final FileDownloadService service;

    @PostMapping("api/files/download")
    public void download(
            @RequestParam String path,
            HttpServletResponse response
    ) {
        var file = new File(path);
        if (!file.exists()) throw new IllegalArgumentException();
        service.download(file, response);
    }
}
