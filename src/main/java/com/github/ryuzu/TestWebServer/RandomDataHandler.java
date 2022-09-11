package com.github.ryuzu.TestWebServer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RandomDataHandler implements HttpHandler {
    private Random random = new Random();
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var csv = Files.readAllLines(Path.of("memo.csv"), Charset.forName("windows-31j"));
        var headers = csv.get(0).split(",");
        var data = new ArrayList<Map<String, String>>();
        for (var i = 1; i < csv.size(); i++) {
            var map = new LinkedHashMap<String, String>();
            var values = csv.get(i).split(",");
            for (var j = 0; j < values.length; j++) {
                map.put(headers[j], values[j]);
            }
            data.add(map);
        }

        var content = data.get(random.nextInt(data.size()));
        var jsonByte = gson.toJson(content).getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, jsonByte.length);
        var output = exchange.getResponseBody();
        output.write(jsonByte);
        output.close();
    }
}
