package com.gateway.filter;

import com.gateway.model.EndpointInfo;
import com.gateway.model.ServiceEndpoints;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    public List<ServiceEndpoints> serviceEndpoints;
    public RouterValidator(
            @Qualifier("book-service") ServiceEndpoints bookServiceEndpoints,
            @Qualifier("library-service") ServiceEndpoints libraryServiceEndpoints
    ) {
        serviceEndpoints = List.of(bookServiceEndpoints, libraryServiceEndpoints);
    }
    public Predicate<ServerHttpRequest> isSecured =
            req -> serviceEndpoints
                    .stream()
                    .anyMatch(serviceEndpoint -> checkRequestMethod(req, serviceEndpoint));
    private boolean checkRequestMethod(ServerHttpRequest request, ServiceEndpoints serviceEndpoints) {
        EndpointInfo endpointInfo = requestedEndpointName(request);
        String endpointName = endpointInfo.getUri();
        if (endpointName == null) return false;
        HttpMethod requestMethod = request.getMethod();
        List<HttpMethod> forbiddenMethods = endpointInfo.getSecuredMethods();
        return forbiddenMethods.contains(requestMethod);
    }
    private EndpointInfo requestedEndpointName(ServerHttpRequest request) {
        String requestPath = request.getURI().getPath();
        for(ServiceEndpoints endpoint : serviceEndpoints) {
            List<EndpointInfo> endpointInfoList = endpoint.getEndpointInfoList();
            for (EndpointInfo endpointInfo : endpointInfoList) {
                if (requestPath.contains(endpointInfo.getUri()))
                    return endpointInfo;
            }
        }
        return null;
    }

}
