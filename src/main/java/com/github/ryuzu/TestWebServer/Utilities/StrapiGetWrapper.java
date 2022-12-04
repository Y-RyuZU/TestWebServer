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

public class StrapiGetWrapper<T> {
    private final String database;
    private final HttpHeaders headers = new HttpHeaders();
    private final RestTemplate restTemplate;
    private final String scheme;
    private final int port;

    public StrapiGetWrapper(String database, RestTemplateBuilder builder, String token) {
        this.database = database;
        this.scheme = "http";
        this.port = 1337;
        this.restTemplate = builder.defaultHeader("Authorization", "bearer " + System.getenv(token)).build();
    }


    public StrapiGetWrapper(String scheme, int port, String database, RestTemplateBuilder builder, String token) {
        this.database = database;
        this.scheme = scheme;
        this.port = port;
        this.restTemplate = builder.defaultHeader("Authorization", "bearer " + System.getenv(token)).build();
    }

    private ResponseEntity<Data<Identify<T>[]>> response(String query) throws URISyntaxException {
        return restTemplate.exchange(new RequestEntity<>(
                null,
                headers,
                HttpMethod.GET,
                new URI(scheme, null, "strapi", port, "/api/" + database , query , null)
        ), new ParameterizedTypeReference<>() {});
    }

    public Identify<T>[] getWithId() throws URISyntaxException {
        return response("").getBody().data;
    }

    public T[] get() throws URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValueAsString(response("").getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Arrays.stream(response("").getBody().data()).map(x -> x.attributes).toArray(size -> (T[]) new Object[size]);
    }

    public T[] find(Map<String, String> filters) throws URISyntaxException {
        var query = filters.entrySet().stream().map(x -> "filters" + x.getKey() + "=" + x.getValue()).collect(Collectors.joining("&", "?", ""));
        return Arrays.stream(response(query).getBody().data()).map(x -> x.attributes).toArray(size -> (T[]) new Object[size]);
    }

    record Data<T>(T data){}
    record Identify<T>(int id, T attributes) {}

}
