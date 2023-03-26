package com.github.ryuzu.TestWebServer.security.configuration;

import com.github.ryuzu.TestWebServer.security.filter.OAuth2AccountFilter;
import com.github.ryuzu.TestWebServer.security.service.AccountDetailsService;
import com.github.ryuzu.TestWebServer.security.service.OAuth2SuccessHadler;
import com.github.ryuzu.TestWebServer.utilities.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {
    private final AccountDetailsService userDetailsService;
    private final OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> userService;
//    private final OAuth2SuccessHadler oAuth2SuccessHadler;
    private final OAuth2AccountFilter oAuth2AccountFilter;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChains(HttpSecurity http) throws Exception {

        http.userDetailsService(userDetailsService);

        http.authorizeHttpRequests()
                .requestMatchers("/api/**" , "/").permitAll()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/mod/**").hasRole(Role.MOD.name())
                .requestMatchers("/auth" ).authenticated()
//                .anyRequest().permitAll();
        ;


        http.oauth2Login()
                .tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient)
                .and()
                .userInfoEndpoint().userService(userService)
//                .and()
//                .successHandler(oAuth2SuccessHadler)

        ;
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true);

        http.addFilter(oAuth2AccountFilter);

        http.cors().disable();
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder encoder) throws Exception {
        var authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
        return authenticationManagerBuilder.build();
//        return authenticationConfiguration.getAuthenticationManager();
    }
}