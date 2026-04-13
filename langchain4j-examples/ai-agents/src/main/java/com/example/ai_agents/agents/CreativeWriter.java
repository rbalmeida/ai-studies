package com.example.ai_agents.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.springframework.stereotype.Component;

public interface CreativeWriter {
    @UserMessage("""
            You are a creative writer.
            Generate a draft of a story no more than
            3 sentences log around the given topic.
            Return only the story and nothing else.
            The topic is {{topic}}.
            """)
    @Agent("Generates a story based on the given topic")
    String generateStory(@V("topic") String topic);
}