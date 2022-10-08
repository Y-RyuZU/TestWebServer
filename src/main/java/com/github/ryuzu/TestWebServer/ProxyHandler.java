package com.github.ryuzu.TestWebServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ProxyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        var queries = exchange.getRequestURI().getRawQuery().split("&");
        var headers = new HashMap<String, String>();
        for (var query : queries) {
            var decoded = URLDecoder.decode(query.split("=")[1], StandardCharsets.UTF_8);
            headers.put(query.split("=")[0], decoded);
        }
        String src = headers.get("url");
        var request = HttpRequest.newBuilder(URI.create(src))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
                .build();
        try {
            var responce = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());
            exchange.getResponseHeaders().add("Content-Type", responce.headers().firstValue("Content-Type").orElse("application/octet-stream"));
            System.out.println(responce.headers().firstValue("Content-Type").orElse("application/octet-stream"));
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, responce.body().length);
            var output = exchange.getResponseBody();
            output.write(responce.body());
            output.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
