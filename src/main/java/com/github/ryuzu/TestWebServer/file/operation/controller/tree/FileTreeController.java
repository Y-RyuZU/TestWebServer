package com.github.ryuzu.TestWebServer.file.operation.controller.tree;

import com.github.ryuzu.TestWebServer.Main;
import com.github.ryuzu.TestWebServer.utilities.WildcardParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequiredArgsConstructor
public class FileTreeController {
    private final FileTreeService service;

    @GetMapping("/api/files/tree/get/**")
    @CrossOrigin("http://localhost:5173")
    public String getFiles(
            @WildcardParam String path
    ) {
        System.out.println("path: " + path);
        return Main.gson.toJson(service.getDirectoryTree(new File(System.getProperty("user.dir") + "/" + "src/main")));
    }
}
