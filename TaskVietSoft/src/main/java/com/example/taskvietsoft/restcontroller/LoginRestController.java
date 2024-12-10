package com.example.taskvietsoft.restcontroller;

import com.example.taskvietsoft.common.JwtHelPer;
import com.example.taskvietsoft.common.ResponseWrapper;
import com.example.taskvietsoft.common.UnauthorizedException;
import com.example.taskvietsoft.entity.UserEntity;
import com.example.taskvietsoft.service.impl.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LoginRestController {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelPer jwtHelPer;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity request) {
        try {
            // Kiểm tra nếu username là null hoặc rỗng
            if (request.getUsername() == null || request.getUsername().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseWrapper<>(null, "Username is required"));
            }

            // Tải thông tin người dùng bằng username
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());

            // Kiểm tra mật khẩu
            if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseWrapper<>(null, "Invalid username or password"));
            }

            // Lấy các vai trò (roles) của người dùng từ userDetails
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            // Nếu username và mật khẩu hợp lệ, sinh token (Bao gồm username và roles)
            String token = jwtHelPer.genToKen(request.getUsername(), roles,3600000);

            // Trả về token trong Response
            return ResponseEntity.ok(new ResponseWrapper<>(token, "Success"));

        } catch (UsernameNotFoundException e) {
            // Nếu username không tồn tại
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseWrapper<>(null, "User not found"));
        }
    }
    @PostMapping("/authenticate")
    public Boolean authenticate(HttpServletRequest request) {
        String token = extractTokenFromHeader(request);
        if (!jwtHelPer.verifyToken(token)) {
            throw new UnauthorizedException("Token is invalid or expired.");
        }
        return true; // Nếu token hợp lệ
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7); // Trả về token sau "Bearer "
        }
        return null;
    }
}
