package com.example.taskvietsoft.common;

public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete");

    private final String permission;

    // Constructor để gán giá trị chuỗi cho Enum
    Permission(String permission) {
        this.permission = permission;
    }

    // Getter để lấy giá trị chuỗi của Enum
    public String getPermission() {
        return permission;
    }

    @Override
    public String toString() {
        return this.permission;
    }
}


