package com.github.ryuzu.TestWebServer.security.sign.up;

import com.github.ryuzu.TestWebServer.redis.database.member.MemberRepository;
import com.github.ryuzu.TestWebServer.security.entity.SignupForm;
import com.github.ryuzu.TestWebServer.security.service.AccountDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private static final String SIGN_UP_URL = "api/signup";
    private final AccountDetailsService service;
    private final MemberRepository repository;

    @PostMapping("/" + SIGN_UP_URL)
    public String signup(@RequestBody SignupForm body, BindingResult result) {
        if (result.hasErrors()) return SIGN_UP_URL;
        if (repository.existsMemberByDisplayName(body.getUsername())) return SIGN_UP_URL;
        try {
            service.register(body.getUsername(), body.getPassword());
        } catch (DataAccessException e) {
            return SIGN_UP_URL;
        }
        return "redirect:/";
    }
}
