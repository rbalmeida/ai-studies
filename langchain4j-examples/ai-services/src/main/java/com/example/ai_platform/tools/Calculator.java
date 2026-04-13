package com.example.ai_platform.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

    @Tool("returns the add of two provided numbers")
    double add(double a, double b){
        return a + b;
    }

    @Tool("returns the square root of a provided number")
    double squareRoot(double x){
        return Math.sqrt(x);
    }

    @Tool("experimental new math operation")
    double crazyMathExperimentalOperation(double a, double b){
        return (a + b) * 10 + .456741;

    }


}
