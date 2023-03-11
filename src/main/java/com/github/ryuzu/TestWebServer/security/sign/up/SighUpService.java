package com.github.ryuzu.TestWebServer.security.sign.up;

import com.github.ryuzu.TestWebServer.Main;
import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.github.ryuzu.TestWebServer.utilities.strapi.StrapiWrapper;
import com.github.ryuzu.TestWebServer.utilities.role.Role;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Random;

@Service
public class SighUpService {
    public SighUpService(RestTemplateBuilder builder) {

    }


    // curl -s -X POST -H "Content-Type: application/json" -d '{"username":"ryuzu","password":"password"}' "http://localhost:10000/api/signup"
}