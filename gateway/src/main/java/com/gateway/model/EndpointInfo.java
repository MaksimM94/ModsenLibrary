package com.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class EndpointInfo {
    private String uri;
    private List<HttpMethod> securedMethods;
}
