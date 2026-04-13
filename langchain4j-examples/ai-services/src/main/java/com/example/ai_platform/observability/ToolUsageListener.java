package com.example.ai_platform.observability;

import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ToolUsageListener implements ChatModelListener {

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        ChatModelListener.super.onResponse(responseContext);

        var toolCalls = responseContext.response().aiMessage().toolExecutionRequests();
        if (toolCalls != null && !toolCalls.isEmpty()) {
            toolCalls.forEach(toolCall -> {
                log.info("AI requested tool execution: name='{}', args='{}'",
                        toolCall.name(),
                        toolCall.arguments());
            });
        }

    }
}
