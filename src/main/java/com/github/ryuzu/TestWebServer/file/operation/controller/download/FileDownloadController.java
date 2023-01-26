package com.github.ryuzu.TestWebServer.file.operation.controller.download;

import com.github.ryuzu.TestWebServer.utilities.annotations.WildcardParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@RequiredArgsConstructor
public class FileDownloadController {
    private final FileDownloadService service;

    @PostMapping("api/files/download/**")
    @CrossOrigin("http://localhost:5173")
    public void download(
            @WildcardParam String path,
            HttpServletResponse response
    ) {
        var file = new File(path);
        if (!file.exists()) throw new IllegalArgumentException();
        service.download(file, response);
    }
}
