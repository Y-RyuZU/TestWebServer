package com.github.ryuzu.TestWebServer.security.SignUp;

import com.github.ryuzu.TestWebServer.Main;
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
        // パスワードの強度を10に設定(4～31)
        String hashedPassword = Main.passwordEncoder.encode(body.password());
        service.post(body.username(), hashedPassword);
    }

    @PostMapping("/api/signin")
    public String signin(@RequestBody AccountForm body) throws URISyntaxException {
        return service.check(body.username(), body.password());
    }

    record AccountForm(String username, String password) {}
}
