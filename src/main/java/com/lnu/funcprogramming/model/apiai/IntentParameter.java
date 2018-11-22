package com.lnu.funcprogramming.model.apiai;

import lombok.Data;

import java.util.List;

@Data
public class IntentParameter {

    private String dataType;
    private String defaultValue;
    private Boolean isList;
    private String name;
    private List<String> prompts;
    private Boolean required;
    private String value;

}
