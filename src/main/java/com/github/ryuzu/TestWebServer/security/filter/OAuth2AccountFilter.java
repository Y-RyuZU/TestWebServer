package com.github.ryuzu.TestWebServer.security.filter;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.github.ryuzu.TestWebServer.redis.database.member.MemberRepository;
import com.github.ryuzu.TestWebServer.security.entity.AccountDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2AccountFilter extends GenericFilterBean {
    private final MemberRepository repository;
    private final AuthenticationManager manager;
//    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2LoginAuthenticationToken)
            this.doFilter((HttpServletResponse) response);
        chain.doFilter(request, response);
    }

    private void doFilter(HttpServletResponse response) {
        var oauth2 = (OAuth2LoginAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
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
//        SecurityContextHolder.getContext().setAuthentication(auth);
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
//        SecurityContextHolder.getContextHolderStrategy().saveContext(context, request, response);
    }
}
