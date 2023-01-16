package com.github.ryuzu.TestWebServer.security.sign.up;

import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
public class SignUpController {
    private SighUpService service;

    public SignUpController(SighUpService service) {
        this.service = service;
    }

    @PostMapping("/api/signup")
    public void signup(@RequestBody AccountForm body) throws URISyntaxException {
        service.post(body);
    }

    record AccountForm(String username, String password) {}
}
