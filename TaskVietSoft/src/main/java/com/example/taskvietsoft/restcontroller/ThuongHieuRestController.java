package com.example.taskvietsoft.restcontroller;

import com.example.taskvietsoft.common.AppConstants;
import com.example.taskvietsoft.common.ResponseWrapper;
import com.example.taskvietsoft.dto.ThuongHieuDTO;
import com.example.taskvietsoft.service.ThuongHieuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/thuong-hieu")
//nơi chứa các api (Đường dẫn)
//chưc năng chính :
// Cung cấp endpoint lấy danh sách thuong_hieu có hỗ trợ phân trang.
// Xử lý các tình huống khi không có dữ liệu hoặc giá trị phân trang không hợp lệ.
public class ThuongHieuRestController {
    private final ThuongHieuService thuongHieuService;
    @Operation(summary = "Lấy danh sách thương hiệu và phân trang (INTEGER pageNo,INTEGER pageSize)" +
            "pageNo:Đây là số của trang mà bạn muốn lấy dữ liệu ; " +
            "pageSize :Đây là số lượng bản ghi tối đa mà bạn muốn trả về trong một trang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công, trả về danh sách sản phẩm"),
            @ApiResponse(responseCode = "404", description = "Đường dẫn sai hoặc dữ liệu không tồn tại"),
            @ApiResponse(responseCode = "500", description = "Giá trị pageNo pageSize khong hợp lệ"),
    })
    @GetMapping()
    public ResponseEntity<ResponseWrapper<Page<ThuongHieuDTO>>> getAllProductsByidSP(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize) {
            Page<ThuongHieuDTO> thuongHieuPage = thuongHieuService.getAll(pageNo, pageSize);
            return ResponseEntity.ok(new ResponseWrapper<>(thuongHieuPage,"Thành công"));
    }
}
