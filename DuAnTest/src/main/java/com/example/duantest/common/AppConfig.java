package com.example.duantest.common;


import com.example.duantest.dto.HinhAnhCrud;
import com.example.duantest.dto.SanPhamCRUD;
import com.example.duantest.dto.SanPhamChiTietCRUD;
import com.example.duantest.entity.ChiTietSanPham;
import com.example.duantest.entity.HinhAnh;
import com.example.duantest.entity.SanPham;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Cấu hình ModelMapper để xử lý ánh xạ trường cụ thể
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSkipNullEnabled(true);

        // Chỉ ánh xạ trường 'id' cho các thực thể liên quan
        modelMapper.typeMap(SanPham.class, SanPhamCRUD.class).addMappings(mapper -> {
            mapper.map(src -> src.getLoaiSanPham().getId(), SanPhamCRUD::setLoaiSanPham);
            mapper.map(src -> src.getChatLieu().getId(), SanPhamCRUD::setChatLieu);
            mapper.map(src -> src.getKieuDang().getId(), SanPhamCRUD::setKieuDang);

        });
        // Chỉ ánh xạ trường 'id' cho các thực thể liên quan
        modelMapper.typeMap(ChiTietSanPham.class, SanPhamChiTietCRUD.class).addMappings(mapper -> {
            mapper.map(src -> src.getMauSac().getId(), SanPhamChiTietCRUD::setMauSac);
            mapper.map(src -> src.getKichCo().getId(), SanPhamChiTietCRUD::setKichCo);
            mapper.map(src -> src.getSanPham().getId(), SanPhamChiTietCRUD::setSanPham);

        });
        modelMapper.typeMap(HinhAnh.class, HinhAnhCrud.class).addMappings(mapper -> {
            mapper.map(src -> src.getSanPham().getId(), HinhAnhCrud::setSanPham);


        });
        return modelMapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
