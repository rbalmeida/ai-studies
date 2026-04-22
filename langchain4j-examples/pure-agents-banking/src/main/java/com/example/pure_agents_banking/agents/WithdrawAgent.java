package com.example.pure_agents_banking.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface WithdrawAgent {
    @SystemMessage("""
            You are a banker that can only withdraw US dollars (USD) from a user account,
            use available BankTool for any operation.
            """)
    @UserMessage("""
            Withdraw {{amount}} from {{user}}'s account and return the new balance.
            """)
    @Agent("A banker that withdraw USD from an account")
    String withdraw(@V("user") String user, @V("amount") Double amount);
}
