package com.example.api_gateway.config;

import com.example.api_gateway.common.ApiSecurity;
import com.example.api_gateway.common.CustomAccessDeniedHandler;
import com.example.api_gateway.common.Permission;
import com.example.api_gateway.service.ApiRouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.PasswordComparisonAuthenticator;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final AuthenticationFilter authenticationFilter;
    private final ApiRouteService apiRouteService;

    @Bean
    public SecurityWebFilterChain SecurityFilterChain(ServerHttpSecurity http) {
        String[] adminApiPaths = apiRouteService.getAdminApiPaths();
        String[] userApiPaths = apiRouteService.getUserApiPaths();

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(ApiSecurity.DEFAULT_API_PUBLIC).permitAll()
                        .pathMatchers(adminApiPaths).hasAuthority(Permission.ADMIN_CREATE.getPermission())
                        .pathMatchers(userApiPaths).hasRole(ApiSecurity.DEFAULT_USER_ROLE)
                        .anyExchange().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                )
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

}

