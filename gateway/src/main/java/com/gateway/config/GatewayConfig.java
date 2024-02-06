package com.gateway.config;

import com.gateway.filter.AuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GatewayConfig {
    private AuthenticationFilter authenticationFilter;
    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("book-service",
                        route -> route.path("/book-service/**")
                                .filters(filter -> {
                                    filter.stripPrefix(1);
                                    filter.filter(authenticationFilter);
                                    return filter;
                                }
                                )
                                .uri("lb://book-service"))
                .route("library-service",
                        route -> route.path("/library-service/**")
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://library-service"))
                .route("auth-service",
                        route -> route.path("/auth-service/**")
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://auth-service"))
                .build();
    }
}
