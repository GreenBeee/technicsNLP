package com.lnu.funcprogramming.model.apiai;

import ai.api.model.AIContext;
import ai.api.model.AIEvent;
import lombok.Data;

import java.util.List;

@Data
public class Intent {

    private Boolean auto;
    private List<AIContext> contexts;
    private List<AIEvent> events;
    private Boolean fallbackIntent;
    private String id;
    private Number lastUpdated;
    private String name;
    private String priority;
    private List<IntentResponse> responses;
    private List<String> templates;
    private List<IntentUserSays> userSays;
    private Boolean webhookForSlotFilling;
    private Boolean webhookUsed;

}
