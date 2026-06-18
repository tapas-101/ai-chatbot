package com.aiChatbot.aiChatbot.repository;

import com.aiChatbot.aiChatbot.entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository
        extends JpaRepository<ChatHistory,Long> {
}
