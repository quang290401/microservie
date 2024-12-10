package com.example.api_gateway.config;


import com.example.api_gateway.common.JwtHelber;
import com.example.api_gateway.dto.restpone.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Component // Đánh dấu class này là một bean Spring để Spring có thể tự động phát hiện và quản lý đối tượng này.
@RequiredArgsConstructor // Tạo một constructor cho các trường final trong class (không cần phải tạo constructor thủ công).
@Slf4j // Tạo một đối tượng logger tự động để ghi lại thông tin log.
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true) // Đặt các trường là final (không thể thay đổi giá trị sau khi khởi tạo) và phạm vi truy cập là package-private (chỉ truy cập trong cùng package).
public class AuthenticationFilter implements WebFilter {

    private final ObjectMapper objectMapper; // ObjectMapper để chuyển đối tượng Java thành JSON và ngược lại.
    private final JwtHelber jwtHelber; // JwtHelber để giải mã và xác thực JWT.

    @NonFinal // Cờ này chỉ ra rằng biến này không phải là final, và có thể thay đổi giá trị sau khi khởi tạo.
    private String[] publicEndpoints = {"/login"}; // Danh sách các endpoint công khai (không cần xác thực).

    // Hàm filter kiểm tra và xác thực token từ HTTP request.
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("Security config"); // Ghi log khi bắt đầu xử lý yêu cầu.

        // Kiểm tra nếu endpoint là công khai, không cần xác thực
        if (isPublicEndpoint(exchange.getRequest())) {
            return chain.filter(exchange); // Tiếp tục chuỗi filter mà không cần xác thực.
        }

        // Lấy Authorization Header từ request
        List<String> authenHeader = exchange.getRequest().getHeaders().get("Authorization");
        if (CollectionUtils.isEmpty(authenHeader)) { // Nếu không có Authorization header
            return unauthenticated(exchange.getResponse()); // Trả về phản hồi không được xác thực.
        }

        // Lấy token từ header Authorization
        String token = authenHeader.get(0).replace("Bearer ", ""); // Loại bỏ tiền tố "Bearer "
        log.info("Token: {}", token); // Ghi log token.

        try {
            // Giải mã và xác thực token
            Claims claims = jwtHelber.getRoleFromToken(token); // Lấy thông tin từ token (claims).

            // Lấy thông tin từ token (username và roles)
            String username = claims.getSubject(); // Lấy username từ subject.
            List<String> roles = claims.get("roles", List.class); // Lấy danh sách các roles.

            log.info("Username: {}", username); // Ghi log username.
            log.info("Roles: {}", roles); // Ghi log roles.

            // Tạo danh sách GrantedAuthority từ các roles
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new) // Chuyển đổi các role thành GrantedAuthority.
                    .toList();

            // Tạo một authentication token với username và authorities
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            // Tạo SecurityContext chứa Authentication token
            SecurityContext securityContext = new SecurityContextImpl(authenticationToken);
            // Đưa SecurityContext vào ReactiveSecurityContextHolder để Spring Security biết rằng người dùng đã được xác thực.
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));

        } catch (RuntimeException e) { // Nếu có lỗi trong quá trình giải mã hoặc xác thực token
            log.error("Invalid token: {}", e.getMessage()); // Ghi log lỗi.
            return unauthenticated(exchange.getResponse()); // Trả về phản hồi không được xác thực.
        }
    }

    // Phương thức này trả về phản hồi không được xác thực (HTTP 401 Unauthorized)
    Mono<Void> unauthenticated(ServerHttpResponse response) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(1401) // Mã lỗi 1401 cho "Unauthenticated".
                .message("Unauthenticated") // Thông báo lỗi.
                .build();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(apiResponse); // Chuyển đối tượng ApiResponse thành chuỗi JSON.
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // Nếu có lỗi khi chuyển đổi sang JSON, ném ngoại lệ.
        }

        // Đặt mã trạng thái HTTP là 401 (Unauthorized) và thêm header Content-Type: application/json.
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // Viết phản hồi vào response body.
        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(body.getBytes()))); // Gửi JSON phản hồi.
    }

    // Kiểm tra xem URL của request có phải là một public endpoint hay không.
    private boolean isPublicEndpoint(ServerHttpRequest request){
        // Kiểm tra nếu URL của request khớp với bất kỳ endpoint công khai nào trong danh sách publicEndpoints.
        return Arrays.stream(publicEndpoints)
                .anyMatch(s -> request.getURI().getPath().matches(s));
    }
}
