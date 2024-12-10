package com.example.duantest.service.Spectification;


import com.example.duantest.dto.FiterDTO.SanPhamFiterDTO;
import com.example.duantest.entity.SanPham;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

public class SanPhamSpectifilecation {
    public static Specification<SanPham> buildWhere(SanPhamFiterDTO form) {
        Specification<SanPham> where = Specification.where(null); // Khởi tạo là một Specification trống

        if (form != null) {
            if (form.getTrangThai() != null) {
                CustomSpecification trangThaiSpec = new CustomSpecification("trangThai", form.getTrangThai());
                where = where.and(trangThaiSpec); // Thêm điều kiện tìm kiếm vào Specification
            }
        }

        return where;
    }

    @RequiredArgsConstructor
    static class CustomSpecification implements Specification<SanPham> {
        @NonNull
        private String field;
        @NonNull
        private Object value;

        @Override
        public Predicate toPredicate(
                Root<SanPham> root,
                CriteriaQuery<?> query,
                CriteriaBuilder criteriaBuilder
        ){
            if (field.equals("trangThai") && value instanceof String) {
                return criteriaBuilder.equal(root.get(field), value);
            }

            return null;
        }
    }
}
