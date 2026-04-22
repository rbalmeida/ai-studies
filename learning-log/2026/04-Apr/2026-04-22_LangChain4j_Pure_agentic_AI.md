# Pure Agentic AI in LangChain4j
In the documentation provided [1] there are some examples of usage of Pure Agentic AI and one of them is for a sample Banking System with agents, so I've made some tests using this approach, full project available at [2].

## Tool usage
Initial tests following exactly the examples were not giving proper results and the main cause was that the system was not using the tools properly all the times.  
To resolve that it was necessary to improve the SystemMessage to instruct tool usage more directly, for example:

```java
@SystemMessage("""
        You are a banker that can only credit US dollars (USD) to a user account,
        use available BankTool for any operation.
        """)
```

## Eventual non-deterministic behavior
When testing and invoking the agentic flow a few times, eventually some errors occurred when tool still mas not properly called. For example instead of using the exchange tool it would use a generated text and parsing would fail, causing an internal server error.  
The output response would sometimes vary, given more or less complete information about the result of the request.

## Further tests and studies
The eventual non-deterministic behavior is an aspect that could cause issues in a production solution, so better understanding on how to control and configure the behavior would be important in those settings.  
Usage of improved prompting for the agents and additional guardrails could be approaches for a more robust solution.  
Identify which models work better with tools. Current test used llama3.1 and some other studies could identify models that would perform better, have less non-deterministic behavior when using tools.

## References
1. Pure Agentic AI, available at https://docs.langchain4j.dev/tutorials/agents/#pure-agentic-ai
2. Test project, available at [pure-agents-banking](/langchain4j-examples/pure-agents-banking)