package com.github.ryuzu.TestWebServer;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        var server = HttpServer.create(new InetSocketAddress(10000), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/index.html", new FileHandler("index.html"));
        server.createContext("/csv.html", new FileHandler("csv.html"));
        server.createContext("/csv.js", new FileHandler("csv.js"));
        server.createContext("/api/random_data", new RandomDataHandler());
        server.createContext("/api/proxy", new ProxyHandler());
        server.start();
    }
}
