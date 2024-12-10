package com.example.taskvietsoft.repository;

import com.example.taskvietsoft.entity.SanPhamEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
// Nơi Viết câu truy vấn để lấy dữ liệu
//sử dụng Spring Data JPA để tự động thực hiện các thao tác cơ sở dữ liệu như tìm kiếm, lưu, xóa, cập nhật
// mà không cần viết quá nhiều mã.
public interface SanPhamRepository extends JpaRepository<SanPhamEntity, Long> {
    //    lấy sản phẩm chi tiết theo id
    @Query("SELECT p FROM SanPhamEntity p " +
            "JOIN DanhMucEntity s ON s.id = p.danhMuc.id " +
            "JOIN ThuongHieuEntity t ON t.id = p.thuongHieu.id " +
            "WHERE p.id = :id")
    Optional<SanPhamEntity> findById(@Param("id") Long id);


    //    lấy danh sách sản phẩm chi tiết theo danh mục
    @Procedure(name = "GetSanPhamChiTietByDanhMuc", outputParameterName = "result")
    List<SanPhamEntity> getSanPhamChiTietByDanhMuc(@Param("idDanhMuc") Long idDanhMuc, Pageable pageable);


}







