package com.github.ryuzu.TestWebServer.role.debugger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user/test")
    public String get() {
        return "Hello User!";
    }
}
