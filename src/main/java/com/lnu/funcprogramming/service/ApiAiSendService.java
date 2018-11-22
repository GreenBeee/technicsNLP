package com.lnu.funcprogramming.service;

import ai.api.AIServiceException;
import ai.api.model.AIResponse;
import ai.api.model.Entity;
import com.lnu.funcprogramming.model.apiai.Intent;

import java.util.List;

public interface ApiAiSendService {

    AIResponse sendApiAiRequest(String user, String text);

    //Intents
    List<Intent> getAllIntents();

    void createIntent(Intent newIntent);

    //Entities
    void createEntity(Entity entityParam) throws AIServiceException;

    void createEntityEntry(Entity entity, List<String> entryParam);

    void updateEntityEntry(Entity entity, List<String> entryParam);

    Entity getEntityByName(String name);
}
