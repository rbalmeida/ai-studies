package com.example.ai_platform.services;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AssistantWithMemory {
    String chat(@MemoryId int memoryId, @UserMessage String message);
}


