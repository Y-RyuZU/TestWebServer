package com.github.ryuzu.TestWebServer.file.operation.controller.move;

import com.github.ryuzu.TestWebServer.file.operation.controller.information.FileInformationController;
import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
import com.github.ryuzu.TestWebServer.utilities.WildcardParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileMoveController {
    private final FileMoveService service;
    @PostMapping("api/files/move/**")
    public void move(
            /*@AuthenticationPrincipal AccountDetails details, */
//            @WildcardParam
            @RequestParam String path,
            @RequestParam String to
    ) throws IOException {
        System.out.println("path: " + path);
        var fromFolder = new File(FileInformationController.workingDir + "/" + path);
        var toFolder = new File(FileInformationController.workingDir + "/" + to);
        service.move(null, fromFolder , toFolder);
    }
}
