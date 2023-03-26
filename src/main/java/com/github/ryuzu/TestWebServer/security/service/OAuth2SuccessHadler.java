package com.github.ryuzu.TestWebServer.security.service;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.github.ryuzu.TestWebServer.redis.database.member.MemberRepository;
import com.github.ryuzu.TestWebServer.security.entity.AccountDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import com.github.ryuzu.TestWebServer.security.oauth2.OAuthProvider;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OAuth2SuccessHadler implements AuthenticationSuccessHandler {
    private final MemberRepository repository;
    private final AuthenticationManager manager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        var oauth2 = (OAuth2LoginAuthenticationToken) authentication;
        System.out.println(oauth2.getClientRegistration());
        var uniqueKey = oauth2.getName() + "@" + oauth2.getClientRegistration().getProviderDetails().getIssuerUri();
        repository.findById(uniqueKey).ifPresentOrElse(
                member -> {
                    try {
                        response.sendRedirect("/");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    repository.save(Member.of(uniqueKey, uniqueKey, "", 1));
                    try {
                        response.sendRedirect("/");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        var account = repository.findById(uniqueKey).orElseThrow();
        var details = new AccountDetails(account);
        var auth = manager.authenticate(UsernamePasswordAuthenticationToken.authenticated(details, null, details.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
