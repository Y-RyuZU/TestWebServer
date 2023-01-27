package com.github.ryuzu.TestWebServer.utilities.strapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.ryuzu.TestWebServer.Main;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class StrapiWrapper<T> {
    private final String database;
    private final RestTemplate restTemplate;
    private final String scheme;
    private final int port;

    public StrapiWrapper(String database, RestTemplateBuilder builder, String token) {
        this.database = database;
        this.scheme = "http";
        this.port = 1337;
        this.restTemplate = builder.defaultHeader("Authorization", "bearer " + System.getenv(token)).build();
    }

    public StrapiWrapper(String scheme, int port, String database, RestTemplateBuilder builder, String token) {
        this.database = database;
        this.scheme = scheme;
        this.port = port;
        this.restTemplate = builder.defaultHeader("Authorization", "bearer " + System.getenv(token)).build();
    }

    private @NotNull ResponseEntity<Data<List<Identify<T>>>> response(String query) throws URISyntaxException {
        return restTemplate.exchange(new RequestEntity<>(
                (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), StrapiWrapper.class),
                null,
                HttpMethod.GET,
                new URI(scheme, null, "strapi", port, "/api/" + database, query, null)
        ), new ParameterizedTypeReference<>() {});
    }

    private @NotNull String debug(String query) throws URISyntaxException {
        return restTemplate.getForObject(new URI(scheme, null, "strapi", port, "/api/" + database, query, null), String.class);
    }

    public List<Identify<T>> getWithId() throws URISyntaxException {
        return response("").getBody().data;
    }

    public String post(T data) throws URISyntaxException {
        return restTemplate.postForObject(new URI(scheme, null, "strapi", port, "/api/" + database , null , null), new Data(data), String.class);
    }

    public List<T> find() throws URISyntaxException {
        return find("");
    }

    public List<T> find(Map<String, String> filters) throws URISyntaxException {
        return find(toQuery(filters));
    }

    public List<T> find(String query) throws URISyntaxException {
        return response(query).getBody().data.stream()
                .map(x -> Main.gson.fromJson(Main.gson.toJsonTree(x.attributes), (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), StrapiWrapper.class))
                ).collect(Collectors.toList());
    }

    public T findUnique(Map<String, String> filters) throws URISyntaxException {
        return response(toQuery(filters)).getBody().data.stream()
                .map(x -> Main.gson.fromJson(Main.gson.toJsonTree(x.attributes), (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), StrapiWrapper.class))
                ).findFirst().orElse(null);
    }

    private String toQuery(Map<String, String> filters) {
        return filters.entrySet().stream().map(x -> "filters" + x.getKey() + "=" + x.getValue()).collect(Collectors.joining("&"));
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Data<T>(T data) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Identify<T>(int id, T attributes) {}
}
