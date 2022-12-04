package com.github.ryuzu.TestWebServer.LoginForm.SignUp;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.ryuzu.TestWebServer.Main;
import com.github.ryuzu.TestWebServer.Utilities.StrapiPostWrapper;
import com.github.ryuzu.TestWebServer.Utilities.StrapiUtilities;
import com.github.ryuzu.TestWebServer.Utilities.StrapiGetWrapper;
import lombok.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Arrays;

@Service
public class SighUpService {
    private final StrapiGetWrapper<SignUpRequest> getWrapper;
    private final StrapiPostWrapper<SignUpRequest> postWrapper;

    public SighUpService(RestTemplateBuilder builder) {
        this.getWrapper = new StrapiGetWrapper<>("members", builder, "STRAPI_TOKEN");
        this.postWrapper = new StrapiPostWrapper<>("members", builder, "STRAPI_TOKEN");
    }

    public void post(String username, String password) throws URISyntaxException {
        postWrapper.post(new SignUpRequest(username, password));
    }
    // curl -s -X POST -H "Content-Type: application/json" -d '{\"username\":\"ryuzu\",\"password\":\"password\"}' "http://localhost:10000/api/signup"

    public String check(String username, String password) throws URISyntaxException {
        System.out.println(getWrapper.getWithId()[0]);
        if (Main.passwordEncoder.matches(password, getWrapper.get()[0].getHashedPassword())) {
            return "OK";
        } else {
            return "NG";
        }
    }
    // curl -s -X POST -H "Content-Type: application/json" -d '{\"username\":\"ryuzu\",\"password\":\"password\"}' "http://localhost:10000/api/signin"

    @Value
    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    @JsonTypeName("data")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public static class SignUpRequest {
        String DisplayName;
        String HashedPassword;
    }
}