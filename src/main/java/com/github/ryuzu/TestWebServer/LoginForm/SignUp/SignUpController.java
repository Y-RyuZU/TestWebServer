package com.github.ryuzu.TestWebServer.LoginForm.SignUp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.ryuzu.TestWebServer.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@RestController
public class SignUpController {
    private SighUpService service;

    public SignUpController(SighUpService service) {
        this.service = service;
    }

    @PostMapping("/api/signup")
    public void signup(@RequestBody SignUpBody body) throws JsonProcessingException, URISyntaxException {
        // パスワードの強度を10に設定(4～31)
        String hashedPassword = Main.passwordEncoder.encode(body.password());
        service.post(body.username(), hashedPassword);
    }

    @PostMapping("/api/signin")
    public String signin(@RequestBody SignUpBody body) throws URISyntaxException {
        return service.check(body.username(), body.password());
    }
}

record SignUpBody(String username, String password) {
}
