package com.github.ryuzu.TestWebServer.utilities.annotations;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface WildcardParam {

    @RequiredArgsConstructor
    class Resolver implements HandlerMethodArgumentResolver {

        private final PathMatcher pathMatcher;

        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            return methodParameter.hasParameterAnnotation(WildcardParam.class);
        }

        @Override
        public Object resolveArgument(@NotNull MethodParameter methodParameter, ModelAndViewContainer modeContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
            var servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
            if (servletRequest == null) return null;
            String patternAttribute = servletRequest.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
            String mappingAttribute = servletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
            return this.pathMatcher.extractPathWithinPattern(patternAttribute, mappingAttribute);
        }
    }
}