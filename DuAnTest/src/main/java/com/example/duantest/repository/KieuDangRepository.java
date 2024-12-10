package com.example.duantest.repository;


import com.example.duantest.entity.KieuDang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuDangRepository  extends JpaRepository<KieuDang,Integer> {
}
