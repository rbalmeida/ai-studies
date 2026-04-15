package com.example.ai_agents.controllers;

import com.example.ai_agents.agents.AudienceEditor;
import com.example.ai_agents.agents.CreativeWriter;
import com.example.ai_agents.agents.StyleEditor;
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
    AudienceEditor audienceEditor;
    StyleEditor styleEditor;

    NovelCreatorController(ChatModel model) {

        creativeWriter = AgenticServices
                .agentBuilder(CreativeWriter.class)
                .chatModel(model)
                .outputKey("story")
                .build();

        audienceEditor = AgenticServices
                .agentBuilder(AudienceEditor.class)
                .chatModel(model)
                .outputKey("story")
                .build();

        styleEditor = AgenticServices
                .agentBuilder(StyleEditor.class)
                .chatModel(model)
                .outputKey("story")
                .build();

        /* TODO - Could these be shared beans?
            What are the standards for agents used in the flows in terms of instances and sharing? */
        novelCreator = AgenticServices
                .sequenceBuilder()
                .subAgents(creativeWriter, audienceEditor, styleEditor)
                .outputKey("story")
                .build();
    }

    @GetMapping("/createstory")
    public String createStory(
            @RequestParam(value = "topic", defaultValue = "small elevator talk") String topic
            , @RequestParam(value = "style", defaultValue = "informal") String style
            , @RequestParam(value = "audience", defaultValue = "adults") String audience) {
        Map<String, Object> input = new HashMap<>();
        input.put("topic", topic);
        input.put("style", style);
        input.put("audience", audience);
        return (String) novelCreator.invoke(input);
    }

}
