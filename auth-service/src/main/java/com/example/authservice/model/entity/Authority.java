package com.example.authservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Override
    @Transient
    public String getAuthority() {
        return name;
    }
}
