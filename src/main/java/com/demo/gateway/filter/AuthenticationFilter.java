package com.demo.gateway.filter;

import com.demo.gateway.config.JwtUtils;
import com.demo.gateway.config.RouteValidator;
import com.demo.gateway.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthenticationFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // if exchange doesn't contain url from validator
            if (validator.isSecured.test(exchange.getRequest())) {
                // if header doesn't contain token then throw error
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new APIException("Missing authorization header!");
                }

                String token = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                } else {
                    throw new APIException("Missing proper token!");
                }

                try {
                    jwtUtils.validateJwtToken(token);
                } catch (Exception e) {
                    throw new APIException("Unauthorized access!");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
