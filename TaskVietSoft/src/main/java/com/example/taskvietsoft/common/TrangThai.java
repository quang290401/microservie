package com.example.taskvietsoft.common;
// Mô tả trạng thai của các bảng trong entity,DTO
public enum TrangThai {
    CON_HANG(1, "Còn Hàng"),
    HET_HANG(2, "Hết Hàng");

    private final int code;
    private final String description;

    TrangThai(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
