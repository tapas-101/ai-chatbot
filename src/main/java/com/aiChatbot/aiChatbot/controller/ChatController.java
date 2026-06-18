package com.aiChatbot.aiChatbot.controller;

import com.aiChatbot.aiChatbot.dto.ChatRequest;
import com.aiChatbot.aiChatbot.dto.ChatResponse;
import com.aiChatbot.aiChatbot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}