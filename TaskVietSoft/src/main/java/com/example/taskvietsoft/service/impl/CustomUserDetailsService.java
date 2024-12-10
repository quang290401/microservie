package com.example.taskvietsoft.service.impl;

//import com.example.taskvietsoft.common.JwtHelPer;
import com.example.taskvietsoft.common.Permission;

import com.example.taskvietsoft.entity.RoleEntity;
import com.example.taskvietsoft.entity.UserEntity;
import com.example.taskvietsoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Lớp cấu hình này cung cấp các chi tiết người dùng trong bộ nhớ cho Spring Security.
 * Nó bao gồm các phương thức để tạo người dùng mặc định và mã hóa mật khẩu.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
//    private  final JwtHelPer jwtHelPer;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("username is empty");
        }
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        // Tạo ra danh sách quyền của spring security
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // Lấy ra danh sách quyền của user
        Set<RoleEntity> roleEntities = userEntity.getRoleEntities();
        // duyệt danh sách quyền của user để lấy ra và đưa vào trong spring seucrity, để spring security xác định được
        // các quyền của user đó
        for (RoleEntity roleEntity : roleEntities) {
            // thềm từng phần tử quyền cho security
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roleEntity.getName()));
            // Lấy danh sách permissions của role và thêm vào grantedAuthorities
            Set<Permission> permissions = roleEntity.getPermissions(); // Nếu dùng Permission Enum
            for (Permission permission : permissions) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission())); // Thêm quyền cho mỗi permission
            }
        }

        // trả về đối tượng user với các thông tin username, passowrd, roles để spring security thực hiện
        // xác thực với username, password được truyền lên từ FE
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // Trả về một PasswordEncoder sử dụng BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }
}
