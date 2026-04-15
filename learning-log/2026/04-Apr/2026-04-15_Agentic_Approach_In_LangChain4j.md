# Experiments with the Agentic Approach with LangChain4j
LangChain4j provides in a general level two approaches to Ai Development, Ai Services and Agentic Systems.  
Ai Services works more as the name suggests, services backed by Ai, for example LLM Models.  
Agentic Systems allow for a more complex architecture and orchestration of agents that use LLM and other capabilities like tools.

## Model performance locally
I've experimented a few models to run locally on a laptop with NVIDIA GeForce RTX 4070 Laptop GPU.

Recent models gemma4 and gemma4:e2b had performance issues and timeouts with the simple sequence workflow for agents, and from the logs it was observed that not all work was being done in GPU.
A smaller model, gemma2:2b was a better fit for current hardware and tests.

This is one architectural aspect to understand well specially when designing this agentic systems, as the load may be higher.  


## Further experiments
How to properly plan and provision the required spec for a certain agent load, in terms of GPU, GPU memory?  
Possibly instead of complete models, use models tailored to the task in question, for exemple a smaller text only model if the workflow is text related only.
