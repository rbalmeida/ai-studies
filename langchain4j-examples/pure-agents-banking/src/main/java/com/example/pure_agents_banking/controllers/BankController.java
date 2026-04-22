package com.example.pure_agents_banking.controllers;

import com.example.pure_agents_banking.agents.CreditAgent;
import com.example.pure_agents_banking.agents.ExchangeAgent;
import com.example.pure_agents_banking.agents.WithdrawAgent;
import com.example.pure_agents_banking.tools.BankTool;
import com.example.pure_agents_banking.tools.ExchangeTool;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;
import dev.langchain4j.model.chat.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banking")
public class BankController {

    BankTool bankTool = new BankTool();
    SupervisorAgent bankSupervisor;

    BankController(ChatModel model){
        bankTool.createAccount("Mario", 1000.0);
        bankTool.createAccount("Georgios", 1000.0);

        WithdrawAgent withdrawAgent = AgenticServices
                .agentBuilder(WithdrawAgent.class)
                .chatModel(model)
                .tools(bankTool)
                .build();

        CreditAgent creditAgent = AgenticServices
                .agentBuilder(CreditAgent.class)
                .chatModel(model)
                .tools(bankTool)
                .build();

        ExchangeAgent exchangeAgent = AgenticServices
                .agentBuilder(ExchangeAgent.class)
                .chatModel(model)
                .tools(new ExchangeTool())
                .build();

        bankSupervisor = AgenticServices
                .supervisorBuilder()
                .chatModel(model)
                .subAgents(withdrawAgent, creditAgent, exchangeAgent)
                .responseStrategy(SupervisorResponseStrategy.SUMMARY)
                .build();

    }

    @GetMapping("/transaction")
    public String executeTransactionForMessage(
            @RequestParam(value = "message", defaultValue = "return instructions") String message) {
        return bankSupervisor.invoke(message);
    }
}


