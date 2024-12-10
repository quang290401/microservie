package com.example.api_gateway.common;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.MediaType;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/**
 * Lớp CustomAccessDeniedHandler dùng để xử lý ngoại lệ chung cho toàn bộ ứng dụng.
 * Giúp đơn giản hóa việc xử lý lỗi trong từng controller và trả về các phản hồi HTTP tương ứng.
 */
public class CustomAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String responseBody = """
            {
                "error": "Access Denied",
                "message": "Bạn không có quyền truy cập vào tài nguyên này.",
                "status": 403
            }
        """;

        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse()
                        .bufferFactory()
                        .wrap(responseBody.getBytes())));
    }
}


