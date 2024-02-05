package com.gateway.config;

import com.gateway.model.EndpointInfo;
import com.gateway.model.ServiceEndpoints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.List;

@Configuration
public class EndpointConfig {
    @Bean("book-service")
    public ServiceEndpoints bookService() {
        EndpointInfo booksEndpointInfo =
                EndpointInfo
                        .builder()
                        .uri("/books")
                        .securedMethods(
                                List.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH)
                        ).build();

        return new ServiceEndpoints("book-service", List.of(booksEndpointInfo));
    }
    @Bean("library-service")
    public ServiceEndpoints libraryService() {
        EndpointInfo booksEndpointInfo =
                EndpointInfo
                        .builder()
                        .uri("/records")
                        .securedMethods(
                                List.of(HttpMethod.POST, HttpMethod.PATCH)
                        ).build();

        return new ServiceEndpoints("library-service", List.of(booksEndpointInfo));
    }
}
