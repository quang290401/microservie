package com.example.taskvietsoft.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.taskvietsoft.common.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//config và kết nối đến cloudinary
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", AppConstants.DEFAULT_CLOUD_NAME,
                "api_key", AppConstants.DEFAULT_API_KEY,
                "api_secret", AppConstants.DEFAULT_API_SECRET
        ));
    }
}
