package com.example.duantest.repository;


import com.example.duantest.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<ChiTietSanPham,Long>, JpaSpecificationExecutor<ChiTietSanPham> {
}
