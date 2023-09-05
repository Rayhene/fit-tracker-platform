package com.example.demo;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account", r -> r.path("/account/**")
                        .uri("lb://account"))
                .route("treino", r -> r.path("/treinos/**")
                        .uri("lb://treino"))
                .build();
    }
}
