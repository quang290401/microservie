package com.example.securitytest.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("sen-data")
public class SentoService {
    /**
     * Nhận dữ liệu từ client dưới dạng một chuỗi JSON (request body), và thực hiện xử lý hoặc ghi log thông tin nhận được.
     * Hàm này có thể được sử dụng để nhận dữ liệu từ các service khác gửi đến, sau đó có thể xử lý hoặc lưu trữ.
     *
     * @param requestBody chuỗi JSON chứa dữ liệu cần xử lý từ client.
     * @return {@code ResponseEntity<String>} đối tượng phản hồi xác nhận dữ liệu đã được nhận thành công.
     */
    @PostMapping("/sento")
    public ResponseEntity<String> receiveData(@RequestBody String requestBody) {
        System.out.println("Received data: " + requestBody);
        // Log dữ liệu nhận được hoặc xử lý
        return ResponseEntity.ok("Dữ liệu đã được nhận thành công!");
    }
    @GetMapping("/hello")
    public String StringHome() {
       return "hello";
    }
}
