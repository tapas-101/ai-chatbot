package com.aiChatbot.aiChatbot.service;

import com.aiChatbot.aiChatbot.entity.ChatHistory;
import com.aiChatbot.aiChatbot.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RestTemplate restTemplate;


    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.url}")
    private String groqUrl;
    private final ChatHistoryRepository chatHistoryRepository;

    public String getResponse(String userMessage) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> requestBody = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", userMessage
                        )
                )
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(
                        groqUrl,
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

        Map body = response.getBody();

        List choices = (List) body.get("choices");

        Map firstChoice = (Map) choices.get(0);

        Map message = (Map) firstChoice.get("message");

        String answer =
                (String) message.get("content");

        ChatHistory history = new ChatHistory();

        history.setQuestion(userMessage);
        history.setAnswer(answer);
        history.setCreatedAt(LocalDateTime.now());

        chatHistoryRepository.save(history);

        return answer;


    }
    public List<ChatHistory> getAllHistory() {

        return chatHistoryRepository.findAll();

    }
}