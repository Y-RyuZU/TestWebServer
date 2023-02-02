package com.github.ryuzu.TestWebServer.security.oauth2;

import com.github.ryuzu.TestWebServer.security.oauth2.providers.DiscordLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.web.client.RestOperations;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class RestOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final RestOperations restOperations;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String userInfoUrl = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();

        var headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", userRequest.getAccessToken().getTokenValue()));
        headers.set(HttpHeaders.USER_AGENT, DiscordLoginService.DISCORD_BOT_USER_AGENT);

        var typeReference = new ParameterizedTypeReference<Map<String, Object>>() {};

        ResponseEntity<Map<String, Object>> responseEntity = restOperations.exchange(userInfoUrl, HttpMethod.GET, new HttpEntity<>(headers), typeReference);

        Map<String, Object> userAttributes = responseEntity.getBody();
        Set<GrantedAuthority> authorities = Collections.singleton(new OAuth2UserAuthority(userAttributes));

        return new DefaultOAuth2User(authorities, userAttributes, userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName());
    }
}
