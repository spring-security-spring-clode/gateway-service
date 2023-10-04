package com.demo.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/service1FallBack")
    public Mono<String> service1FallBack() {
        return Mono.just("Service 1 is down, Please try again later!");
    }

    @RequestMapping("/service2FallBack")
    public Mono<String> service2FallBack() {
        return Mono.just("Service 2 is down, Please try again later!");
    }

    @RequestMapping("/auth-service-fallback")
    public Mono<String> authServiceFallback() {
        return Mono.just("Auth service is down");
    }
}
