package com.github.ryuzu.TestWebServer.role.debugger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModController {
    @GetMapping("/mod/test")
    public String get() {
        return "Hello Mod!";
    }
}
