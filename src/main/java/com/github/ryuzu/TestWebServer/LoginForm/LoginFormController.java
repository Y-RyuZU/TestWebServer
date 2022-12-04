package com.github.ryuzu.TestWebServer.LoginForm;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginFormController {
    @PutMapping("/api/loginform")
    public static String put(@CookieValue("key1") String cookieValue) {
        return "Hello, World!";
    }

}
