package com.example.ai_platform.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ItemStatus {

    Map<String, String> MockStatus =
            Map.of("IT001", "Pending",
                    "IT002", "Shipped",
                    "IT003", "In Process",
                    "IT005", "Approved",
                    "IT008", "Approved",
                    "IT009", "Approved");


    @Tool("Get ths status of the item given its ID")
    String getItemStatus(String item){
        return MockStatus.getOrDefault(item, "Pending");
    }

}
