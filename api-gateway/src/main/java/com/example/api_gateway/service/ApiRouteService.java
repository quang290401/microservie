package com.example.api_gateway.service;

import com.example.api_gateway.entity.ApiRoute;
import com.example.api_gateway.repository.ApiRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiRouteService {

    private final ApiRouteRepository apiRouteRepository;

    public String[] getAdminApiPaths() {
        List<ApiRoute> adminRoutes = apiRouteRepository.findByRole("admin");
        return adminRoutes.stream().map(ApiRoute::getRoutePattern).toArray(String[]::new);
    }

    public String[] getUserApiPaths() {
        List<ApiRoute> userRoutes = apiRouteRepository.findByRole("user");
        return userRoutes.stream().map(ApiRoute::getRoutePattern).toArray(String[]::new);
    }
}
