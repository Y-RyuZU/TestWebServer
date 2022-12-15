package com.github.ryuzu.TestWebServer.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String value = request.getHeader("X-AUTH-TOKEN");
        SecurityContextHolder.getContext().setAuthentication(getToken(value));
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getToken(String value) throws UnsupportedEncodingException {
        if(value == null || !value.startsWith("Bearer ")) return null;
        String token = value.substring(7);
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret")).build().verify(token);
        String username = decodedJWT.getClaim("username").asString();
        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
}
