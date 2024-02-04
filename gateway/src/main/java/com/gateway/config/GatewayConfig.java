package com.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

//https://mayankposts.medium.com/adding-security-to-micro-services-spring-boot-gateway-filter-jwt-authentication-part-2-4ba8cb572312
@Configuration
public class GatewayConfig {
    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book-service",
                        route -> route.path("/book-service/**")
                                .and()
                                .method(HttpMethod.GET)
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://book-service"))
                .route("book-service",
                        route -> route.path("/book-service/**")
                                .and()
                                .method(HttpMethod.POST)
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://book-service"))
                .route("library-service",
                        route -> route.path("/library-service/**")
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://library-service"))
                .build();
    }
}
