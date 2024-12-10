package com.example.api_gateway.dto.restpone;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // Thiết lập mức độ truy cập mặc định cho các trường là private.
@JsonInclude(JsonInclude.Include.NON_NULL) // Chỉ bao gồm các trường có giá trị khác null khi chuyển đối tượng thành JSON.
public class ApiResponse<T> {
    @Builder.Default // Đảm bảo rằng nếu không cung cấp giá trị cho trường này trong builder, nó sẽ mặc định có giá trị 1000.
    private int code = 1000; // Mã phản hồi, mặc định là 1000, có thể thay đổi nếu cần.

    private String message; // Thông báo phản hồi, có thể chứa các thông tin chi tiết về phản hồi.

    private T result; // Kết quả trả về, kiểu dữ liệu có thể thay đổi tùy vào loại dữ liệu mà người sử dụng mong muốn (generic type).
}