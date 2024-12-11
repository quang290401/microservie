package com.example.api_gateway.config;

import com.example.api_gateway.common.ApiSecurity;
import com.example.api_gateway.common.CustomAccessDeniedHandler;
import com.example.api_gateway.common.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class  SecurityConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean // Đánh dấu phương thức này để Spring tạo ra một bean trong context của ứng dụng.
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                // Tắt bảo vệ CSRF nếu không cần thiết trong ứng dụng.
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF protection thường được tắt trong ứng dụng REST API.

                .authorizeExchange(exchanges -> exchanges // Cấu hình quyền truy cập cho các endpoint.
                        .pathMatchers(ApiSecurity.DEFAULT_API_PUBLIC).permitAll() // Các endpoint công khai có thể truy cập cho tất cả người dùng.
                        .pathMatchers(ApiSecurity.DEFAULT_API_ADMIN).hasAuthority(Permission.ADMIN_CREATE.getPermission()) // Các endpoint chỉ cho phép người dùng có quyền "ADMIN_CREATE".
                        .pathMatchers(ApiSecurity.DEFAULT_API_USER).hasRole(ApiSecurity.DEFAULT_USER_ROLE) // Các endpoint yêu cầu người dùng có vai trò "USER".
                        .anyExchange().authenticated() // Mọi endpoint khác yêu cầu người dùng phải xác thực.
                )

                // Cấu hình xử lý ngoại lệ khi quyền truy cập bị từ chối.
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(new CustomAccessDeniedHandler()) // Sử dụng handler tùy chỉnh để xử lý khi người dùng không có quyền truy cập.
                )

                // Thêm custom filter vào chuỗi bảo mật tại vị trí AUTHENTICATION.
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION); // Đặt filter xác thực tùy chỉnh (authenticationFilter) vào chuỗi xử lý bảo mật tại vị trí AUTHENTICATION.

        return http.build(); // Trả về đối tượng cấu hình đã xây dựng.
    }

}
