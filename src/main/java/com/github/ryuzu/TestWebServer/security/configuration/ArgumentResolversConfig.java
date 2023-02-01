package com.github.ryuzu.TestWebServer.security.configuration;

import com.github.ryuzu.TestWebServer.utilities.annotations.WildcardParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ArgumentResolversConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new WildcardParam.Resolver(pathMatcher()));
    }

    @Bean
    public PathMatcher pathMatcher() {
        return new AntPathMatcher();
    }
}
