package com.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ServiceEndpoints {
    private String name;
    private List<EndpointInfo> endpointInfoList;
}
