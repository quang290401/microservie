package com.example.taskvietsoft.entity;

import com.example.taskvietsoft.common.Permission;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.HashSet;


@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Đại diện cho bảng roles trong cơ sở dữ liệu
//tương tác với cơ sở dữ liệu, giúp thao tác dữ liệu dễ dàng hơn.
public class RoleEntity extends SuperEntity {
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(length = 255)
    private String description;

    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "permission")
    private Set<Permission> permissions = new HashSet<>();
    @ManyToMany(mappedBy = "roleEntities")
    private Set<UserEntity> userEntities = new HashSet<>();
}
