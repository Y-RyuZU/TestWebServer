package com.github.ryuzu.TestWebServer.file.operation.controller.upload;

import com.github.ryuzu.TestWebServer.file.operation.controller.information.FileInformationController;
import com.github.ryuzu.TestWebServer.security.entity.AccountDetails;
import com.github.ryuzu.TestWebServer.utilities.annotations.WildcardParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService service;

    @PostMapping("api/files/upload/**")
    @CrossOrigin("http://localhost:5173")
    public void upload(
            @AuthenticationPrincipal AccountDetails details,
            @WildcardParam String path,
            @RequestParam("file") MultipartFile file
    ) {
        var folder = new File(FileInformationController.workingDir + "/"+ path + "/" + file.getOriginalFilename());
        service.upload(details, folder, file);
    }
}
