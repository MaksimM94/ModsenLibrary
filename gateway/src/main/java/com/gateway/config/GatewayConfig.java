package com.gateway.config;

import com.gateway.filter.AuthenticationFilter;
import com.gateway.filter.RouterValidator;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

//https://mayankposts.medium.com/adding-security-to-micro-services-spring-boot-gateway-filter-jwt-authentication-part-2-4ba8cb572312
@Configuration
@AllArgsConstructor
public class GatewayConfig {
    private RouterValidator validator;
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
                .build();
    }
}
