package com.example.securitytest.config;


import com.example.securitytest.common.AppCommon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.*;

/**
 * Cấu hình RSA cho ứng dụng, bao gồm việc tạo và cung cấp các cặp khóa công khai và khóa riêng tư.
 * Các khóa này sẽ được sử dụng trong việc mã hóa và giải mã dữ liệu.
 */
@Configuration
public class RsaConfig {

    /**
     * Tạo cặp khóa RSA (công khai và riêng tư) với độ dài khóa là 2048 bit.
     * Cặp khóa này được tạo ra chỉ một lần và sử dụng cho các yêu cầu mã hóa và giải mã sau này.
     *
     * @return cặp khóa RSA bao gồm khóa công khai và khóa riêng tư.
     * @throws Exception nếu có lỗi khi tạo cặp khóa.
     */
    @Bean
    public KeyPair keyPair() throws Exception {
        // Khởi tạo một đối tượng KeyPairGenerator với thuật toán RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(AppCommon.RSA);
        // Thiết lập độ dài khóa RSA là 2048 bit
        keyPairGenerator.initialize(AppCommon.KEY_SIZE);
        // Tạo và trả về cặp khóa RSA
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * Cung cấp khóa công khai từ cặp khóa đã được tạo.
     * Khóa công khai này sẽ được sử dụng để mã hóa dữ liệu.
     *
     * @param keyPair cặp khóa RSA được tạo ra từ bean keyPair.
     * @return khóa công khai.
     */
    @Bean
    public PublicKey publicKey(KeyPair keyPair) {
        // Trả về khóa công khai từ cặp khóa
        return keyPair.getPublic();
    }

    /**
     * Cung cấp khóa riêng tư từ cặp khóa đã được tạo.
     * Khóa riêng tư này sẽ được sử dụng để giải mã dữ liệu đã được mã hóa bằng khóa công khai.
     *
     * @param keyPair cặp khóa RSA được tạo ra từ bean keyPair.
     * @return khóa riêng tư.
     */
    @Bean
    public PrivateKey privateKey(KeyPair keyPair) {
        // Trả về khóa riêng tư từ cặp khóa
        return keyPair.getPrivate();
    }
}

