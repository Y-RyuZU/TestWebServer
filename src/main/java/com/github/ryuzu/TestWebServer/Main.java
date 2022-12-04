package com.github.ryuzu.TestWebServer;

import com.github.ryuzu.TestWebServer.Archives.RandomDataHandler;
import com.github.ryuzu.TestWebServer.Archives.RootHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    public static void main(String[] args) throws IOException {

    }
}
