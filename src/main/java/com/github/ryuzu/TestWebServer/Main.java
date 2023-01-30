package com.github.ryuzu.TestWebServer;

import com.github.ryuzu.TestWebServer.discord.DiscordBot;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.net.InetSocketAddress;

@SpringBootApplication//(exclude = {JacksonAutoConfiguration.class,SecurityAutoConfiguration.class})
public class Main extends SpringBootServletInitializer {
    public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    public static final Gson gson = new Gson();

    public static void main(String[] args) {
        DiscordBot.initialize();
        SpringApplication.run(Main.class, args);
    }
}
