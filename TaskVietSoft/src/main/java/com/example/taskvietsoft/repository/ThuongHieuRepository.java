package com.example.taskvietsoft.repository;

import com.example.taskvietsoft.entity.ThuongHieuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
// Nơi Viết câu truy vấn để lấy dữ liệu
//sử dụng Spring Data JPA để tự động thực hiện các thao tác cơ sở dữ liệu như tìm kiếm, lưu, xóa, cập nhật
// mà không cần viết quá nhiều mã.
public interface ThuongHieuRepository extends JpaRepository<ThuongHieuEntity, Long> {

}
