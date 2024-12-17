package com.example.taskvietsoft.config;

import com.example.taskvietsoft.common.ApiSecurity;
import com.example.taskvietsoft.common.AppConstants;
import com.example.taskvietsoft.common.Permission;
//import com.example.taskvietsoft.restcontroller.filter.JwtFilTer;
import com.example.taskvietsoft.restcontroller.filter.JwtFilTer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.AbstractSecurityBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Lớp cấu hình bảo mật cho Spring Security. Định nghĩa các quy tắc bảo mật cho ứng dụng, bao gồm:
 * - Cấu hình các đường dẫn yêu cầu quyền truy cập khác nhau.
 * - Xác thực cơ bản với HTTP Basic Authentication.
 * - Quy tắc CSRF cho các đường dẫn API nhất định.
 */
@Configuration
@EnableMethodSecurity  // Bật bảo mật cho các phương thức, cho phép bảo vệ các phương thức cụ thể
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilTer jwtFilTer;
    /**
     * Cấu hình bảo mật cho các yêu cầu HTTP.
     * Phương thức này định nghĩa cách mà các yêu cầu sẽ được bảo vệ.
     *
     * @param http Đối tượng HttpSecurity để cấu hình bảo mật HTTP.
     * @return SecurityFilterChain đã được cấu hình.
     * @throws Exception Nếu có lỗi xảy ra trong quá trình cấu hình.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Cấu hình CSRF (Cross-Site Request Forgery) - bảo mật chống lại các cuộc tấn công CSRF
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(ApiSecurity.DEFAULT_API_ADMIN)  // Tắt CSRF cho các đường dẫn admin (chẳng hạn như upload ảnh)
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Không sử dụng session
                )
                // Cấu hình quyền truy cập cho các đường dẫn HTTP
                .authorizeHttpRequests(requests -> requests
                        // Các đường dẫn công khai, không yêu cầu xác thực
                        .requestMatchers(ApiSecurity.DEFAULT_API_PUBLIC).permitAll()
                        // Các đường dẫn yêu cầu quyền ADMIN
                        .requestMatchers(ApiSecurity.DEFAULT_API_ADMIN).hasAuthority(Permission.ADMIN_READ.getPermission())
                        // Các đường dẫn yêu cầu quyền USER hoặc ADMIN
                        .requestMatchers(ApiSecurity.DEFAULT_API_USER).hasAnyRole(AppConstants.DEFAULT_USER_ROLE,AppConstants.DEFAULT_ADMIN_ROLE)

                        // Các yêu cầu còn lại yêu cầu xác thực
                        .anyRequest().authenticated()


                )
                // nếu token hợp lệ, người dùng sẽ được xác thực và có thể truy cập các tài nguyên mà không cần phải đăng nhập lại.
                // Nếu token không hợp lệ, hệ thống sẽ trả về lỗi và ngừng xử lý yêu cầu tiếp theo.
                .addFilterBefore(jwtFilTer, UsernamePasswordAuthenticationFilter.class);
//                .httpBasic(Customizer.withDefaults());


                // Cấu hình sử dụng HTTP Basic Authentication (sử dụng cho các API đơn giản, không yêu cầu giao diện người dùng phức tạp)


        // Trả về đối tượng SecurityFilterChain đã được cấu hình
        return http.build();
    }
}
