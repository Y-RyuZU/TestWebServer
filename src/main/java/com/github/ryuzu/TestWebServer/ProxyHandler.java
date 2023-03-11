package com.github.ryuzu.TestWebServer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProxyHandler {
    @RequestMapping("/api/proxy")
    public static ResponseEntity<byte[]> proxy(@RequestParam("url") String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
                .build();
        var send = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(send.headers().firstValue("Content-Type").map(MediaType::valueOf).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(send.body());
    }
}
