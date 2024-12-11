package com.example.api_gateway.repository;

import com.example.api_gateway.entity.ApiRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApiRouteRepository extends JpaRepository<ApiRoute, Long> {
    List<ApiRoute> findByRole(String role);
}