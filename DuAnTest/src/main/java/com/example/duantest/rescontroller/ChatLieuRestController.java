package com.example.duantest.rescontroller;

import com.example.duantest.dto.ChatLieuDTO;
import com.example.duantest.service.ChatLieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-lieu")
public class ChatLieuRestController {
    private final ChatLieuService chatLieuService;

    @GetMapping("/getAll")
    public List<ChatLieuDTO> getAllMauSac() {
        return chatLieuService.getAllChatLieu();
    }
    @PostMapping()
    public ChatLieuDTO addChatLieu(@RequestBody ChatLieuDTO chatLieuDTO) {
        return chatLieuService.addChatLieu(chatLieuDTO);

    }

    @PutMapping()
    public ChatLieuDTO updateChatLieu(@RequestBody ChatLieuDTO chatLieuDTO) {
        return chatLieuService.updateChatLieu(chatLieuDTO);

    }

}
