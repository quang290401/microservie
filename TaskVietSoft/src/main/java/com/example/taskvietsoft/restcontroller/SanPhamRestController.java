package com.example.taskvietsoft.restcontroller;

import com.example.taskvietsoft.common.AppConstants;
//import com.example.taskvietsoft.common.JwtHelPer;
import com.example.taskvietsoft.common.ResponseWrapper;
import com.example.taskvietsoft.dto.SanPhamDTO;
import com.example.taskvietsoft.service.SanPhamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/sp-ct")
//nơi chứa các api (Đường dẫn)
//chưc năng chính :
//  Cung cấp endpoint lấy san_pham dựa theo id.
//  Cung cấp endpoint lấy danh sách san_pham dựa theo idDanhMuc.
//  Xử lý các tình huống khi không có dữ liệu hoặc giá trị phân trang không hợp lệ.
public class SanPhamRestController {
    private final SanPhamService sanPhamService;

    @Operation(summary = "Lấy  sản phẩm chi tiết theo SanPham_Id(Long)" +
            "Lấy danh sách thương hiệu và phân trang (INTEGER pageNo,INTEGER pageSize)" +
            "pageNo:Đây là số của trang mà bạn muốn lấy dữ liệu ;" +
            " pageSize :Đây là số lượng bản ghi tối đa mà bạn muốn trả về trong một trang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công, trả về  sản phẩm"),
            @ApiResponse(responseCode = "404", description = "Không có sản phẩm nào trong dữ liệu"),
            @ApiResponse(responseCode = "400", description = "Sai kiểu dữ liệu truyền vào"),
    })
    @GetMapping("/detail/{id}") // Đường dẫn được sửa lại
    public ResponseEntity<ResponseWrapper<SanPhamDTO>> findById(@PathVariable Long id) {
        SanPhamDTO sanPhamChiTiet = sanPhamService.findById(id);
        return ResponseEntity.ok(new ResponseWrapper<>(sanPhamChiTiet, "thành công")); // Trả về 200 OK với DTO
    }



    @Operation(summary = "Lấy danh sách sản phẩm chi tiết theo danh mục_Id(Long)" +
            "Lấy danh sách thương hiệu và phân trang (INTEGER pageNo,INTEGER pageSize)" +
            " pageNo:Đây là số của trang mà bạn muốn lấy dữ liệu," +
            " pageSize :Đây là số lượng bản ghi tối đa mà bạn muốn trả về trong một trang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công, trả về danh sách sản phẩm"),
            @ApiResponse(responseCode = "404", description = "Không có sản phẩm nào trong dữ liệu"),
            @ApiResponse(responseCode = "400", description = "Sai kiểu dữ liệu truyền vào"),
            @ApiResponse(responseCode = "500", description = "Giá trị pageNo,pageSize không hợp lệ"),
    })
    @GetMapping("/danh-muc/{id}")
    public ResponseEntity<ResponseWrapper<List<SanPhamDTO>>> getSanPhamChiTietByDanhMuc(
            @PathVariable Long id,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize) {
        List<SanPhamDTO> sanPhamChiTietDTOS = sanPhamService.getSanPhamChiTietByDanhMuc(id, pageNo, pageSize);
        if (sanPhamChiTietDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(Collections.emptyList(), "Không có sản phẩm nào "));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(sanPhamChiTietDTOS, "thành công"));
    }
}
