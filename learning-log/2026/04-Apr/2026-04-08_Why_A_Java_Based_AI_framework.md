# Why a Java based AI framework
For many years I've been working with Java in professional and personal projects and specially and it is the main language I've used.

A few years ago I started learning Machine Learning and AI, and in most experiments and studies I've used Python.

When I started thinking about architectures and integrations to use AI to improve existing systems, I've mainly used a decoupled architecture, for example exposing a Python AI component usign Rest APIs or messaging systems.

Then I started thinking, how could I implement something direclty in Java if I wanted this approach, if I wanted a seamless integration or direct integration into existing systems?

## LangChain4j: a Java framework
Evaluating the possibilties I got to know LangChain4j inspired in the Python based frameworks which have evaluated before, so that seemed a natural choice.

I've read thru the documentation and samples and I was able to understand and implement sample projects very fast, specially with what they call High Level APIs, a declarative approach to defining the components and configuration for the framework.

The integration with Spring Boot is also a very positive factor, I was able to evolve naturally Spring Boot based applications with the addition of AI Components from the LangChain4j framework. In a couple of days I was able to implement some initial POCs with very good results and thats a very positive factor and usable resource to adopt in existing enterprise Java systems, like the complex ERPs and other back-office platforms I've been working for the last decades.

## Some initial POCs I've made
Some examples with chat assistants are here: [langchain4j-examples](/langchain4j-examples/ai-platform/src/main/java/com/example/ai_platform/)

One great idea is to facilitate the end user interaction in complex systems. For example for a quick query on the status of a transaction could be obtained in a chat assistant, instead of going thru a series of menus and interfaces.
For that, the chat assitant could be enhanced with tools integrated with the core systems.

In this sample code I used a mock, but it could be an api or query on a real system.

[ItemStatus.java](/langchain4j-examples/ai-platform/src/main/java/com/example/ai_platform/tools/ItemStatus.java)

With the High level API @Tool we declare a tool that can be used by the LLM model.
```java
    @Tool("Get ths status of the item given its ID")
    String getItemStatus(String item){
        return MockStatus.getOrDefault(item, "Pending");
    }
```

With that, instead of going thru several menus, the user could ask questions to a chat interface like:

```
User: what is the status of item IT004
Assistant: The status of item IT004 is still pending.
```

## Further experiments
The initiall experiments were very satisfyingin terms of clarity and speed and easy of integration and I'm looking forward to other possibilities.