package com.example.taskvietsoft.restcontroller.filter;

import com.example.taskvietsoft.common.JwtHelPer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilTer extends OncePerRequestFilter {

    private final JwtHelPer jwtHelPer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Lấy token từ header "Authorization" trong request
        String token = extractTokenFromHeader(request);

        // Nếu token không null, tiếp tục xử lý xác thực
        if (token != null) {
            try {
                // Kiểm tra token có hợp lệ không bằng cách sử dụng phương thức verifyTokenWithRSA của JwtHelPer
                if (jwtHelPer.verifyToken(token)) {
                    List<String> roles = jwtHelPer.extractRoles(token);
                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new) // Chuyển mỗi vai trò thành một GrantedAuthority
                            .toList();
                    // Nếu token hợp lệ, tạo một đối tượng UsernamePasswordAuthenticationToken trống
                    // (ở đây bạn có thể thêm thông tin người dùng nếu cần)
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken("username", null, authorities);

                    // Lấy SecurityContext hiện tại và thiết lập Authentication là đối tượng vừa tạo
                    // Điều này có nghĩa là người dùng đã được xác thực
                    System.out.printf("FFFF"+authorities.toString());
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authenticationToken);
                } else {
                    // Nếu token không hợp lệ, ném ra một SecurityException
                    throw new SecurityException("Invalid JWT token");
                }

                // In ra console token hợp lệ (chỉ để kiểm tra trong quá trình phát triển)
                System.out.println("Valid token: " + token);

            } catch (Exception e) {
                // Nếu có bất kỳ lỗi nào xảy ra trong quá trình kiểm tra hoặc xác thực token
                // Gửi phản hồi lỗi với mã trạng thái 401 Unauthorized và thông báo lỗi
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or Missing Token");
                return; // Ngừng tiếp tục chuỗi filter nếu token không hợp lệ
            }
        }

        // Tiếp tục chuỗi filter để các filter khác có thể xử lý (nếu có)
        filterChain.doFilter(request, response);
    }


    private String extractTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7); // Trả về token sau "Bearer "
        }
        return null;
    }
}


