package com.example.taskvietsoft.restcontroller;

import com.example.taskvietsoft.common.ResponseWrapper;
import com.example.taskvietsoft.dto.HinhAnhDTO;
import com.example.taskvietsoft.service.HinhAnhService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("api/hinh-anh")
@RequiredArgsConstructor
//nơi chứa các api (Đường dẫn)
//chưc năng chính :
//Xử lý các tình huống khi dữ liêu truyền vào không hợp lê.
// lấy ảnh từ server local đưa lên Cloudnidary lưu đường dẫn vào database
public class HinhAnhRestController {
    private final HinhAnhService hinhAnhService;

    @Operation(summary = "Upload hình ảnh cho sản phẩm, truyền vào 1 file ảnh và idSanPham (MultipartFile file, Long idSanPham)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công, trả về sản phẩm"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ, ví dụ như không có file được chọn"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy sản phẩm với ID được cung cấp"),
            @ApiResponse(responseCode = "500", description = "Lỗi khi upload ảnh hoặc xử lý khác liên quan đến server(Sai kích cỡ , định dạng)"),
    })
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file, @RequestParam Long id) {
        // Kiểm tra nếu không có file
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không có file được chọn");
        }
        try {
            // Tải lên ảnh và lấy URL ảnh
            String imageUrl = hinhAnhService.uploadImage(file);
            // Gọi service để lưu đường dẫn hình ảnh vào DB
            hinhAnhService.upLoadAnh(imageUrl, id);

            // Trả về thông báo thành công
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"Upload thành công!\"}");

        } catch (IOException ex) {
            // Xử lý khi có lỗi upload ảnh
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi upload ảnh hoặc xử lý khác liên quan đến server: " + ex.getMessage());
        }
    }


}
