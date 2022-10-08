package com.github.ryuzu.TestWebServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var responseBody = "Success!".getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, responseBody.length);
        var output = exchange.getResponseBody();
        output.write(responseBody);
        output.close();
    }
}
