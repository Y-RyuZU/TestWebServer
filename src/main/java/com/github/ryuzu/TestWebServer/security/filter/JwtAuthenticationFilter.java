package com.github.ryuzu.TestWebServer.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ryuzu.TestWebServer.security.entity.AccountEntity;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/signin", "POST"));
        setUsernameParameter("username");
        setPasswordParameter("password");
        this.setAuthenticationSuccessHandler((req, res, ex) -> {
            String token = JWT.create()
                    .withIssuer("com.github.ryuzu")
                    .withClaim("username", ex.getName())
                    .sign(Algorithm.HMAC256("secret"));
            res.addCookie(new Cookie("JWT", token));
            res.setStatus(200);
        });

        this.setAuthenticationFailureHandler((req, res, ex) -> {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AccountEntity account = new Gson().fromJson(request.getReader(), AccountEntity.class);
            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getDisplayName(), account.getHashedPassword(), List.of(new SimpleGrantedAuthority("USER")))
            );
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}