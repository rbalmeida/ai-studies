package com.example.ai_agents.controllers;

import com.example.ai_agents.agents.CreativeWriter;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.UntypedAgent;
import dev.langchain4j.model.chat.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/novelcreator")
public class NovelCreatorController
{

    UntypedAgent novelCreator;
    CreativeWriter creativeWriter;

    NovelCreatorController(ChatModel model) {

        creativeWriter = AgenticServices
                .agentBuilder(CreativeWriter.class)
                .chatModel(model)
                .outputKey("story")
                .build();

        novelCreator = AgenticServices
                .sequenceBuilder()
                .subAgents(creativeWriter)
                .outputKey("story")
                .build();


    }

    @GetMapping("/createstory")
    public String createStory(@RequestParam(value = "topic", defaultValue = "small elevator talk") String topic) {
        Map<String, Object> input = new HashMap<>();
        input.put("topic", topic);
        return (String) novelCreator.invoke(input);
    }

}
