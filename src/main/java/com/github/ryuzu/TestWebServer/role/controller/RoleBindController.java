package com.github.ryuzu.TestWebServer.role.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.ryuzu.TestWebServer.utilities.strapi.StrapiWrapper;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.net.URISyntaxException;
import java.util.List;

public class RoleBindController {
    private static final StrapiWrapper<Permisson> permissonWrapper = new StrapiWrapper<>("permissons", new RestTemplateBuilder(), "STRAPI_TOKEN") {};

    public static void bindRole(String role, String path) throws URISyntaxException {
        permissonWrapper.post(new Permisson(role, List.of(new AccessablePath(path))));
    }

    public static void unbindRole() {

    }

    @JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    record AccessablePath(String Path) {

    }

    @JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    record Permisson(String Role, List<AccessablePath> AccessablePaths) {

    }
}
