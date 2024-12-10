package com.example.duantest.repository;


import com.example.duantest.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu,Integer> {


}
