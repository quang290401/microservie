package com.example.taskvietsoft.restcontroller;

import com.example.taskvietsoft.common.AppConstants;
//import com.example.taskvietsoft.common.JwtHelPer;
import com.example.taskvietsoft.common.ResponseWrapper;
import com.example.taskvietsoft.dto.DanhMucDTO;
import com.example.taskvietsoft.service.DanhMucService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("api/danh-muc")
//nơi chứa các api (Đường dẫn)
//chưc năng chính :
//  Cung cấp endpoint lấy danh sách danh_muc có hỗ trợ phân trang.
//  Xử lý các tình huống khi không có dữ liệu hoặc giá trị phân trang không hợp lệ.
public class DanhMucRestController {
    private final DanhMucService danhMucService;
//    private final JwtHelPer jwtHelPer;

    @Operation(summary = "Lấy danh sách  danh muc" +
            "Lấy danh sách thương hiệu và phân trang (INTEGER pageNo,INTEGER pageSize)" +
            " pageNo:Đây là số của trang mà bạn muốn lấy dữ liệu ;" +
            " pageSize :Đây là số lượng bản ghi tối đa mà bạn muốn trả về trong một trang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công, trả về danh sách sản phẩm"),
            @ApiResponse(responseCode = "404", description = "Không có sản phẩm nào trong dữ liệu"),
            @ApiResponse(responseCode = "500", description = "Giá trị pageNo pageSize khong hợp lệ"),
    })
    @GetMapping()
    public ResponseEntity<ResponseWrapper<Page<DanhMucDTO>>> getAllProductsByidSP(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize) {

        Page<DanhMucDTO> danhMucPage = danhMucService.getAll(pageNo, pageSize);

        if (danhMucPage.isEmpty()) {
            // Trả về mã 404 với Page rỗng và thông báo
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(Page.empty(), "Không có sản phẩm nào trong danh mục."));
        }

        return ResponseEntity.ok(new ResponseWrapper<>(danhMucPage, "Thành công"));
    }
}
