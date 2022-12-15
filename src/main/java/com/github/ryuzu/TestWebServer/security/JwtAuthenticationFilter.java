package com.github.ryuzu.TestWebServer.security;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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
            res.setHeader("X-AUTH-TOKEN", token);
            res.setStatus(200);
        });

        this.setAuthenticationFailureHandler((req, res, ex) -> {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // あとで作成するLoginFormクラスを、リクエストのパラメータとマッピングして作成する
            AccountEntity account = new Gson().fromJson(request.getReader(), AccountEntity.class);
            // 作成したLoginFormクラスの内容でログインの実行をする
            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        String token = resolveToken(request);
//        if (token == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            authentication(verifyToken(token));
//        } catch (JWTVerificationException e) {
//            log.error("verify token error", e);
//            SecurityContextHolder.clearContext();
//            ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
//        }
//        filterChain.doFilter(request, response);
//    }

//    private String resolveToken(ServletRequest request) {
//        String token = ((HttpServletRequest) request).getHeader("Authorization");
//        if (token == null || !token.startsWith("Bearer ")) {
//            return null;
//        }
//        return token.substring(7);
//    }
//
//    private DecodedJWT verifyToken(String token) {
//        JWTVerifier verifier = JWT.require(algorithm).build();
//        return verifier.verify(token);
//    }
//
//    private void authentication(DecodedJWT jwt) {
//        Long userId = Long.valueOf(jwt.getSubject());
//        userRepository.findById(userId).ifPresent(user -> {
//            SimpleLoginUser simpleLoginUser = new SimpleLoginUser(user);
//            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(simpleLoginUser, null, simpleLoginUser.getAuthorities()));
//        });
//    }

}