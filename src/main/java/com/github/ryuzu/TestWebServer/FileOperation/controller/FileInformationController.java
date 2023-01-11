package com.github.ryuzu.TestWebServer.FileOperation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class FileInformationController {
    @GetMapping("/api/files")
    public String getFiles() {
        String workingdir = "D:\\Test1";

        File file1 = new File(workingdir);
        File[] list = file1.listFiles();

        for (File f: list){
            if(f.isFile()) {
                System.out.println(f);
            }
            if(f.isDirectory()) {
                System.out.println(f);
            }
        }

        return "Hello World";
    }
}
