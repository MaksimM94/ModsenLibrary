package com.gateway.filter;

import com.gateway.model.EndpointInfo;
import com.gateway.model.ServiceEndpoints;
import org.springframework.beans.factory.annotation.Qualifier;
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
                    .noneMatch(
                            serviceEndpoint -> checkUri(req, serviceEndpoint.getEndpointInfoList())
                                    /*serviceEndpoint.getEndpointInfoList().stream().anyMatch(
                                    endpointInfo -> checkRequestMethod(req, endpointInfo))*/
                    );
    private boolean checkRequestMethod(ServerHttpRequest request, List<EndpointInfo> endpointInfo) {
        System.out.println(request.getMethod().name());
        //System.out.printf("EndpointInfo name:%s\n",endpointInfo.getValue());
        //List<HttpMethod> forbiddenMethods = endpointInfo.getSecuredMethods();
        //return forbiddenMethods.contains(request.getMethod());
        return endpointInfo.stream().noneMatch(endpointInf -> endpointInf.getSecuredMethods().stream().noneMatch(httpMethod -> httpMethod.equals(request.getMethod())));
    }

    private boolean checkUri(ServerHttpRequest request, List<EndpointInfo> endpointInfoList) {
        System.out.println(request.getURI().getPath());
        return endpointInfoList.stream().noneMatch(
                endpointInfo -> endpointInfo.getUri().contains(request.getURI().getPath())
        );
    }

}
