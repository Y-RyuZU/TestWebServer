package com.github.ryuzu.TestWebServer.Strapi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StrapiTest {
    @RequestMapping("/api/strapi/test")
    public static String put() {

        return "Hello, Strapi!";
    }
}
