package com.github.ryuzu.TestWebServer.security.configuration;

import com.github.ryuzu.TestWebServer.utilities.role.Role;
import com.github.ryuzu.TestWebServer.utilities.annotations.WildcardParam;
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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {

    private final UserDetailsService userDetailsService;

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

        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true);

        http.cors().disable();
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PathMatcher pathMatcher() {
        return new AntPathMatcher();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new WildcardParam.Resolver(pathMatcher()));
    }
}