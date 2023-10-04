package com.demo.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> routeNotToValidate = List.of(
            "/service1/auth/register",
            "/service1/auth/token",
            "/service1/auth/validate",
            "/auth-service/api/auth/**",
            "/auth-service/api/test/**",
            "/auth-service/h2-console/**",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> {
        return routeNotToValidate.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
    };
}
