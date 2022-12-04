package com.github.ryuzu.TestWebServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class FileHandler implements HttpHandler {
    enum FileType {
        HTML("text/html"),
        JavaScript("text/javascript");

        final String type;

        FileType(String type) {
            this.type = type;
        }

        public String getTypeID() {
            return type;
        }
    }

    private String file;
    private FileType type;

    public FileHandler(String file) {
        this.file = file;
        type = fromExtention(Arrays.stream(file.split("\\.")).reduce((a, b) -> b).get());
    }

    public static FileType fromExtention(String extention) {
        switch (extention) {
            case "html":
                return FileType.HTML;
            case "js":
                return FileType.JavaScript;
        }
        return null;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        byte[] resBody = new byte[0];
        var stream = getClass().getResourceAsStream(file).readAllBytes();
        if (stream != null) resBody = stream;
        exchange.getResponseHeaders().add("Content-Type", type.getTypeID());
        exchange.sendResponseHeaders(200, resBody.length);
        var output = exchange.getResponseBody();
        output.write(resBody);
        output.close();
    }
}
