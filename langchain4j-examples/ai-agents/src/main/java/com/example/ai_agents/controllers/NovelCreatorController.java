package com.example.ai_agents.controllers;

import com.example.ai_agents.agents.*;
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

    CreativeWriter creativeWriter;
    AudienceEditor audienceEditor;
    StyleEditor styleEditor;
    StyleScorer styleScorer;

    UntypedAgent novelCreator;
    UntypedAgent styleReviewLoop;
    StyledWriter styledWriter;

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

        styleScorer = AgenticServices
                .agentBuilder(StyleScorer.class)
                .chatModel(model)
                .outputKey("score")
                .build();

        /* TODO - Could these be shared beans?
            What are the standards for agents used in the flows in terms of instances and sharing? */
        novelCreator = AgenticServices
                .sequenceBuilder()
                .subAgents(creativeWriter, audienceEditor, styleEditor)
                .outputKey("story")
                .build();

        styleReviewLoop = AgenticServices
                .loopBuilder()
                .subAgents(styleScorer, styleEditor)
                .maxIterations(5)
                .exitCondition(agenticScope ->
                                agenticScope.readState("score", 0.0) >= 0.8)
                .outputKey("story")
                .build();

        styledWriter = AgenticServices
                .sequenceBuilder(StyledWriter.class)
                .subAgents(creativeWriter, styleReviewLoop)
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

    @GetMapping("/createstoryandscore")
    public String createStoryWithStyleScorer(
            @RequestParam(value = "topic", defaultValue = "small elevator talk") String topic
            , @RequestParam(value = "style", defaultValue = "informal") String style) {

       return styledWriter.writeStoryWithStyle(topic, style);
    }

}
