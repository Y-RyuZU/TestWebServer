package com.github.ryuzu.TestWebServer.security.configuration;

import com.github.ryuzu.TestWebServer.utilities.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {

    private final UserDetailsService userDetailsService;
    private final OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> userService;

    @Bean
    public SecurityFilterChain securityFilterChains(HttpSecurity http) throws Exception {

        // @formatter:off
//        http
//                .formLogin()
//                .loginPage("/api/signin")
//                .loginProcessingUrl("/login").permitAll()
//                .usernameParameter("userName")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/home")             // 認証成功時に遷移するデフォルトのパス
//                .failureUrl("/loginForm?error=true")   // 認証失敗時に遷移するパス
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/loginForm")         // ログアウト成功時に遷移するパス
//                .and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .disable()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        http.csrf().disable();

//        http.exceptionHandling()
//                .accessDeniedPage("/accessDeniedPage");

//        http.cors().configurationSource(corsConfigurationSource());
//        http
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))))
//                .addFilterAfter(new JwtAuthorizationFilter(), JwtAuthenticationFilter.class)
//        ;
        http.userDetailsService(userDetailsService);

        http.authorizeHttpRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/mod/**").hasRole(Role.MOD.name())
//                .anyRequest().permitAll();
        ;


        http.oauth2Login()
                .tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient)
                .and()
                .userInfoEndpoint().userService(userService);
//                .tokenEndpoint().accessTokenResponseClient(new RestOAuth2AccessTokenResponseClient(restOperations()))
//                .and()
//                .userInfoEndpoint().userService(new RestOAuth2UserService(restOperations()));
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true);

        http.cors().disable();
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}