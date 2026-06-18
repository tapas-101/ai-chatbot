package com.aiChatbot.aiChatbot.controller;

import com.aiChatbot.aiChatbot.dto.ChatRequest;
import com.aiChatbot.aiChatbot.dto.ChatResponse;
import com.aiChatbot.aiChatbot.entity.ChatHistory;
import com.aiChatbot.aiChatbot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatResponse askQuestion(
            @RequestBody ChatRequest request) {

        String response =
                chatService.getResponse(request.getMessage());

        return new ChatResponse(response);
    }
    @GetMapping("/history")
    public List<ChatHistory> getHistory() {

        return chatService.getAllHistory();

    }
}