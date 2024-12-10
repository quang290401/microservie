package com.example.duantest.repository;


import com.example.duantest.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham,Integer>, JpaSpecificationExecutor<SanPham> {
    @Query("SELECT sp FROM SanPham sp WHERE sp.trangThai = :trangThai")
    Page<SanPham> findByTrangThai(@Param("trangThai") String trangThai, Pageable pageable);

}
