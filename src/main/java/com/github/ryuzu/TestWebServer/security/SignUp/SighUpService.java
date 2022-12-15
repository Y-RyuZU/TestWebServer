package com.github.ryuzu.TestWebServer.security.SignUp;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.ryuzu.TestWebServer.Main;
import com.github.ryuzu.TestWebServer.Utilities.StrapiWrapper;
import lombok.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class SighUpService {
    private final StrapiWrapper<SignUpRequest> wrapper;
    public SighUpService(RestTemplateBuilder builder) {
        this.wrapper = new StrapiWrapper<>("members", builder, "STRAPI_TOKEN") {};
    }

    public void post(String username, String password) throws URISyntaxException {
        wrapper.post(new SignUpRequest(username, password));
    }
    // curl -s -X POST -H "Content-Type: application/json" -d '{"username":"ryuzu","password":"password"}' "http://localhost:10000/api/signup"

    public String check(String username, String password) throws URISyntaxException {
        if (Main.passwordEncoder.matches(password, wrapper.get().get(0).getHashedPassword())) {
            return "OK";
        } else {
            return "NG";
        }
    }
    // curl -s -X POST -H "Content-Type: application/json" -d '{"username":"ryuzu","password":"password"}' "http://localhost:10000/api/signin"

    @Value
    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    public static class SignUpRequest {
        String DisplayName;
        String HashedPassword;
    }
}