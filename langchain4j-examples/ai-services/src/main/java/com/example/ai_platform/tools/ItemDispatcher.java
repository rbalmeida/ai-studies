package com.example.ai_platform.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class ItemDispatcher {

    @Tool
    String dispatchItem(String item){
        return String.format("Item: %s successfully dispatched", item);
    }
}
