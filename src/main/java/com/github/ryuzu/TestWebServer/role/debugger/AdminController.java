package com.github.ryuzu.TestWebServer.role.debugger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/admin/test")
    public String get() {
        return "Hello Admin!";
    }
}
