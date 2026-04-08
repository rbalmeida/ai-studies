package com.example.ai_platform.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface MathAssistant {
    @SystemMessage("You are a math assistant that preferably use available tools to perform calculations")
    String chat(String userMessage);

}
