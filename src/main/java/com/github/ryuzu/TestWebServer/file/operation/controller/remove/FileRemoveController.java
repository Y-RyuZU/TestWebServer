package com.github.ryuzu.TestWebServer.file.operation.controller.remove;

import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
import com.github.ryuzu.TestWebServer.utilities.annotations.WildcardParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequiredArgsConstructor
public class FileRemoveController {
    private final FileRemoveService service;

    @PostMapping("api/files/remove/**")
    public void remove(@AuthenticationPrincipal AccountDetails details, @WildcardParam String path) {
        var file = new File(path);
        service.remove(details, file);
    }
}
