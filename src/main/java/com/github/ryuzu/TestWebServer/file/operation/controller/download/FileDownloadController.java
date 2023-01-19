package com.github.ryuzu.TestWebServer.file.operation.controller.download;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

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
