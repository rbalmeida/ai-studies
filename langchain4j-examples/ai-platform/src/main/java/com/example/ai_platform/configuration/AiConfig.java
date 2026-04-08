package com.example.ai_platform.configuration;

import com.example.ai_platform.observability.ToolUsageListener;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class AiConfig {


    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    ChatLanguageModel chatLanguageModel(ToolUsageListener toolUsageListener) {
        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434") // Default Ollama port
                .modelName("llama3.1")               // Or your preferred model
                .listeners(Collections.singletonList(toolUsageListener))
                .build();
    }


}
