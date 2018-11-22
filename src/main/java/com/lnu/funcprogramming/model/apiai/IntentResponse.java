package com.lnu.funcprogramming.model.apiai;

import ai.api.model.ResponseMessage;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class IntentResponse {

    private String action;
    private List<IntentAffectedContext> affectedContexts;
    private Map<String, Object> defaultResponsePlatforms;
    private List<ResponseMessage> messages;
    private List<IntentParameter> parameters;
    private Boolean resetContexts;
}
