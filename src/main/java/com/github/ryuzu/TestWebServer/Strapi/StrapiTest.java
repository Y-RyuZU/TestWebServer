package com.github.ryuzu.TestWebServer.Strapi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StrapiTest {
    @GetMapping("/user/test")
    public static String get() {

        return "Hello, User!";
    }
}
