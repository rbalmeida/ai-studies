package com.example.ai_platform.controllers;

import com.example.ai_platform.services.Assistant;
import com.example.ai_platform.services.AssistantWithMemory;
import com.example.ai_platform.services.MathAssistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssistantController
{

    Assistant assistant;
    AssistantWithMemory assistantWithMemory;
    MathAssistant mathAssistant;

    public AssistantController(
            Assistant assistant,
            AssistantWithMemory assistantWithMemory,
            MathAssistant mathAssistant){

        this.assistant = assistant;
        this.assistantWithMemory = assistantWithMemory;
        this.mathAssistant = mathAssistant;
    }

    @GetMapping("/assistant")
    public String assistant(@RequestParam(value = "message", defaultValue = "Write me something simple") String message) {
        return assistant.chat(message);
    }

    @GetMapping("/assistantwithmemory")
    public String assistantWithMemory(@RequestParam(value="memoryId", defaultValue = "0") int memoryId, @RequestParam(value = "message", defaultValue = "Write me something simple") String message) {
        return assistantWithMemory.chat(memoryId, message);
    }

    @GetMapping("/mathassistant")
    public String mathAssistant(@RequestParam(value = "message", defaultValue = "Write me something simple") String message) {
        return mathAssistant.chat(message);
    }

}
