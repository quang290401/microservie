package com.example.duantest.service.impl;

import com.example.duantest.dto.ChatLieuDTO;
import com.example.duantest.entity.ChatLieu;
import com.example.duantest.repository.ChatLieuRepository;
import com.example.duantest.service.ChatLieuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatLieuImpl implements ChatLieuService {
    private final ChatLieuRepository chatLieuRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ChatLieuDTO> getAllChatLieu() {

        List<ChatLieu> chatLieus = chatLieuRepository.findAll();
        return chatLieus.stream()
                .map(entity -> modelMapper.map(entity, ChatLieuDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChatLieuDTO addChatLieu(ChatLieuDTO chatLieuDTO) {
        ChatLieu chatLieu = ChatLieu.builder()
                .ten(chatLieuDTO.getTen())
                .moTa(chatLieuDTO.getMoTa())
                .trangThai(chatLieuDTO.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();
        ChatLieu chatLieu1 = chatLieuRepository.save(chatLieu);
        return modelMapper.map(chatLieu1, ChatLieuDTO.class);
    }

    @Override
    public ChatLieuDTO updateChatLieu(ChatLieuDTO chatLieuDTO) {
        ChatLieu chatLieu = ChatLieu.builder()
                .ten(chatLieuDTO.getTen())
                .moTa(chatLieuDTO.getMoTa())
                .trangThai(chatLieuDTO.getTrangThai())
                .ngayTao(LocalDateTime.now())
                .build();
        chatLieu.setId(chatLieuDTO.getId());
        ChatLieu chatLieu1 = chatLieuRepository.save(chatLieu);
        return modelMapper.map(chatLieu1, ChatLieuDTO.class);
    }
}
