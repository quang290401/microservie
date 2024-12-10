package com.example.taskvietsoft.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.HashSet;

@Data
@Entity
@Table(name = "users")
// Đại diện cho bảng user trong cơ sở dữ liệu
//tương tác với cơ sở dữ liệu, giúp thao tác dữ liệu dễ dàng hơn.
public class UserEntity extends SuperEntity {
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, length = 100)
    private String email;
    @Column(unique = true, length = 100)
    private String fullName;

    @Column(nullable = false)
    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable( // tự động tạo ra bảng phụ user_roles
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"), /* Cấu hình để thuộc tính user_id trong bảng phụ user_roles
                                                           sẽ là khóa phụ tham chiếu tới cột id trong bảng users*/
            inverseJoinColumns = @JoinColumn(name = "role_id")/* Cấu hình để thuộc tính role_id trong bảng phụ user_roles
                                                           sẽ là khóa phụ tham chiếu tới cột id trong bảng roles*/
    )
    // 1 user có nhiều quyền
    private Set<RoleEntity> roleEntities = new HashSet<>();
}
