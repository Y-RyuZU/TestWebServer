package com.github.ryuzu.TestWebServer.Utilities;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class StrapiPostWrapper<T> {
    private final String database;
    private final HttpHeaders headers = new HttpHeaders();
    private final RestTemplate restTemplate;
    private final String scheme;
    private final int port;

    public StrapiPostWrapper(String database, RestTemplateBuilder builder, String token) {
        this.database = database;
        this.scheme = "http";
        this.port = 1337;
        this.restTemplate = builder.defaultHeader("Authorization", "bearer " + System.getenv(token)).build();
    }


    public StrapiPostWrapper(String scheme, int port, String database, RestTemplateBuilder builder, String token) {
        this.database = database;
        this.scheme = scheme;
        this.port = port;
        this.restTemplate = builder.defaultHeader("Authorization", "bearer " + System.getenv(token)).build();
    }

    public String post(T data) throws URISyntaxException {
        return restTemplate.postForObject(new URI(scheme, null, "strapi", port, "/api/" + database , null , null), data, String.class);
    }
}
