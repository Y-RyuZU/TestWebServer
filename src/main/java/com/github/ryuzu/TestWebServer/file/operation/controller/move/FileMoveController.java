package com.github.ryuzu.TestWebServer.file.operation.controller.move;

import com.github.ryuzu.TestWebServer.file.operation.controller.information.FileInformationController;
import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
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
    @PostMapping("api/files/move/{path:.+}")
    public void move(@AuthenticationPrincipal AccountDetails details, @PathVariable String path, @RequestParam("to") String to) throws IOException {
        var fromFolder = new File(FileInformationController.workingDir + "/" + path);
        var toFolder = new File(FileInformationController.workingDir + "/" + to);
        service.move(details, fromFolder , toFolder);
    }
}
