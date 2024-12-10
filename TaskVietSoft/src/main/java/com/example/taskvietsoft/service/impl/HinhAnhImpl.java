package com.example.taskvietsoft.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.taskvietsoft.common.AppConstants;
import com.example.taskvietsoft.dto.HinhAnhDTO;
import com.example.taskvietsoft.entity.HinhAnhEntity;
import com.example.taskvietsoft.entity.SanPhamEntity;
import com.example.taskvietsoft.repository.HinhAnhRepository;
import com.example.taskvietsoft.repository.SanPhamRepository;
import com.example.taskvietsoft.service.HinhAnhService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
//Lấy dữ liệu từ HinhAnhRepository
//Lớp này được gọi ở controller để sử dụng
//Chưc năng chính :upload ảnh cho sản phẩm
public class HinhAnhImpl implements HinhAnhService {
    private final HinhAnhRepository hinhAnhRepository;
    private final SanPhamRepository sanPhamRepository;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary;

    //    upload ảnh cho sản phẩm lưu trữ đường dẫn ảnh vào cơ sở dữ liệu
    @Override
    public HinhAnhDTO upLoadAnh(String filePath, Long idSanPham) {
        // Tìm sản phẩm theo id
        SanPhamEntity sanPham = sanPhamRepository.findById(idSanPham)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm với id: " + idSanPham));
        // Lấy ngày hiện tại dưới dạng LocalDate
        LocalDate currentDate = LocalDate.now();
        // Tạo HinhAnhEntity mới
        HinhAnhEntity hinhAnh = new HinhAnhEntity();
        hinhAnh.setFilePath(filePath);
        hinhAnh.setSanPham(sanPham);
        hinhAnh.setCreateDate(currentDate);
        // Lưu ảnh vào cơ sở dữ liệu
        hinhAnh = hinhAnhRepository.save(hinhAnh);
        // Convert dữ liệu từ Entity sang DTO và trả về
        return modelMapper.map(hinhAnh, HinhAnhDTO.class);
    }


    //upload (tải lên) một tệp hình ảnh lên dịch vụ lưu trữ đám mây (Cloudinary)
    //trả về URL của hình ảnh đã được tải lên.
    public String uploadImage(MultipartFile file) throws IOException {
        // Kiểm tra nếu không có file
        if (file.isEmpty()) {
            throw new IllegalArgumentException("No file selected.");
        }
        // Kiểm tra loại tệp (chỉ chấp nhận .jpg hoặc .png)
        String contentType = file.getContentType();
        if (!AppConstants.DEFAULT_IMAGE_JPG.equals(contentType) && !AppConstants.DEFAULT_IMAGE_PNG.equals(contentType)) {
            throw new IllegalArgumentException("File phải là ảnh định dạng JPG hoặc PNG.");
        }
        // Giới hạn kích thước tệp (ví dụ: 2MB)
        long maxFileSize = AppConstants.DEFAULT_MAX_FILE_SIZE; // 2MB
        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException("Kích thước ảnh không được vượt quá 2MB.");
        }

        // Tải ảnh lên với kích thước cố định (ví dụ: 500x500 pixel)
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", AppConstants.DEFAULT_RESOURCE_TYPE,
                        "width", AppConstants.DEFAULT_MAX_WITH,
                        "height", AppConstants.DEFAULT_MAX_HEIGHT,
                        "crop", AppConstants.DEFAULT_CROP // Đảm bảo ảnh không vượt quá kích thước cố định
                )
        );

        // Lấy URL của ảnh đã tải lên
        return uploadResult.get("url").toString();
    }



}

