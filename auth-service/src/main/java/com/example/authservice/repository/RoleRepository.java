package com.example.authservice.repository;

import com.example.authservice.model.entity.Role;
import com.example.authservice.model.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select a.* from authorities a fetch join roles_authorities ra on a.id = ra.authority_id where ra.role_id = :id;", nativeQuery = true)
    List<Authority> getAllAuthorities(@Param("id") Integer id);
}
