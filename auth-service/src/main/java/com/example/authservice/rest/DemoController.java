package com.example.authservice.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/public")
    @PreAuthorize("hasAuthority('read')")
    public String publicEndPoint() {
        return "Public End Point";
    }
    @GetMapping("/private")
    @PreAuthorize("hasAuthority('write')")
    public String privateEndPoint() {
        return "Private End Point";
    }

}
