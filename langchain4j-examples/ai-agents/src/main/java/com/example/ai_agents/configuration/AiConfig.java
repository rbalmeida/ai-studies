package com.example.ai_agents.configuration;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {


    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    ChatModel chatLanguageModel() {
        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434") // Default Ollama port
                .modelName("gemma4")               // Or your preferred model
                //.listeners(Collections.singletonList(toolUsageListener))
                .build();
    }


}
