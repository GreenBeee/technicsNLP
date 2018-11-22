package com.lnu.funcprogramming.model.apiai;

import lombok.Data;

import java.util.Map;

@Data
public class IntentAffectedContext {
    private Integer lifespan;
    private String name;
    private Map<String, Object> parameters;
}
