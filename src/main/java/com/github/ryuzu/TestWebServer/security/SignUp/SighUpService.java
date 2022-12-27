package com.github.ryuzu.TestWebServer.security.SignUp;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.ryuzu.TestWebServer.Main;
import com.github.ryuzu.TestWebServer.Utilities.StrapiWrapper;
import com.github.ryuzu.TestWebServer.security.entity.AccountEntity;
import com.github.ryuzu.TestWebServer.security.service.Role;
import com.github.ryuzu.TestWebServer.security.service.RoleUtility;
import lombok.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Random;

@Service
public class SighUpService {
    private final StrapiWrapper<AccountEntity> wrapper;
    public SighUpService(RestTemplateBuilder builder) {
        this.wrapper = new StrapiWrapper<>("members", builder, "STRAPI_TOKEN") {};
    }

    public void post(SignUpController.AccountForm form) throws URISyntaxException {
        wrapper.post(new AccountEntity(form.username(), Main.passwordEncoder.encode(form.password()) , new Random().nextInt((int) Math.pow(2, Role.values().length))));
    }
    // curl -s -X POST -H "Content-Type: application/json" -d '{"username":"ryuzu","password":"password"}' "http://localhost:10000/api/signup"
}