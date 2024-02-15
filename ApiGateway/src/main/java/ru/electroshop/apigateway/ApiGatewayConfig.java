package ru.electroshop.apigateway;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
public class ApiGatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product_service", r -> r.path("/api/products/**")
                        .uri("http://localhost:8080"))
                .route("cart_service", r -> r.path("/api/carts/**")
                        .uri("http://localhost:8081"))
                .route("review_service", r -> r.path("/api/reviews/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
