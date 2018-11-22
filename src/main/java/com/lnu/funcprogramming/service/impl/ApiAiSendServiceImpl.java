package com.lnu.funcprogramming.service.impl;

import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Entity;
import ai.api.model.EntityEntry;
import com.lnu.funcprogramming.model.apiai.Intent;
import com.lnu.funcprogramming.service.ApiAiSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ApiAiSendServiceImpl implements ApiAiSendService {

    @Value("${apiai.developer.token}")
    private String apiAiDeveloperToken;
    @Value("${apiai.base.url}")
    private String apiAiUrl;

    private final AIDataService aiDataService;
    private final RestTemplate restTemplate;

    private final String apiAiIntentsUrl = "/intents";
    private final String apiAiEntitiesUrl = "/entities";
    private final String apiAiEntriesUrl = "/entries";
    private final String apiAiVersionParameter = "20150910";
    private static final String replaceLiteral = "\\u2019";

    @Override
    public AIResponse sendApiAiRequest(String user, String text){

        AIRequest aiRequest = new AIRequest(text);
        aiRequest.setSessionId(user);
        aiRequest.setTimezone(Calendar.getInstance().getTimeZone().getID());
        aiRequest.setLanguage("en");

        AIResponse aiResponse = null;

        try {
            aiResponse = aiDataService.request(aiRequest);
        } catch (AIServiceException e) {
            log.error(e.getStackTrace());
        }
        return aiResponse;
    }

    @Override
    public List<Intent> getAllIntents() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiAiDeveloperToken);
        headers.set("Content-Type", "application/json");
        URI url = null;
        try {
            url = new URIBuilder(apiAiUrl + apiAiIntentsUrl)
                    .addParameter("v", apiAiVersionParameter)
                    .build();
        } catch (URISyntaxException e) {
            log.error(e.getStackTrace(), e);
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Intent[]> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Intent[].class);
        if (exchange.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(exchange.getBody());
        } else {
            return null;
        }
    }

    @Override
    public void createIntent(Intent newIntent) {
        List<Intent> existedIntents = getAllIntents();
        boolean existed = existedIntents.stream().map(Intent::getName).collect(Collectors.toList()).contains(newIntent.getName());
        if (existed) {
            return;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiAiDeveloperToken);
        headers.set("Content-Type", "application/json");
        List<Intent> intentsToAdd = Collections.singletonList(newIntent);
        HttpEntity<List<Intent>> entity = new HttpEntity<>(intentsToAdd, headers);

        URI url = null;
        try {
            url = new URIBuilder(apiAiUrl + apiAiIntentsUrl)
                    .addParameter("v", apiAiVersionParameter)
                    .build();
        } catch (URISyntaxException e) {
            log.error(e.getStackTrace(), e);
        }
        restTemplate.postForObject(url, entity, String.class);
    }

    @Override
    public void createEntity(Entity entityParam) throws AIServiceException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiAiDeveloperToken);
        headers.set("Content-Type", "application/json");
        HttpEntity<Entity> entity = new HttpEntity<>(entityParam, headers);

        URI url = null;
        try {
            url = new URIBuilder(apiAiUrl + apiAiEntitiesUrl)
                    .addParameter("v", apiAiVersionParameter)
                    .build();
        } catch (URISyntaxException e) {
            log.error(e.getStackTrace());
        }
        restTemplate.postForObject(url, entity, String.class);
    }

    @Override
    public void createEntityEntry(Entity entity, List<String> entryParam) {
        EntityEntry entry = new EntityEntry(entryParam.get(0), entryParam);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiAiDeveloperToken);
        headers.set("Content-Type", "application/json");
        HttpEntity<EntityEntry> entityEntry = new HttpEntity<>(entry, headers);

        URI url = null;
        try {
            url = new URIBuilder(apiAiUrl + apiAiEntitiesUrl + "/" + entity.getName() + apiAiEntriesUrl)
                    .addParameter("v", apiAiVersionParameter)
                    .build();
        } catch (URISyntaxException e) {
            log.error(e.getStackTrace(), e);
        }
        try {
            restTemplate.postForObject(url, entityEntry, String.class);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        }
    }

    @Override
    public void updateEntityEntry(Entity entity, List<String> entryParam) {
        EntityEntry entry = new EntityEntry(entryParam.get(0), entryParam);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiAiDeveloperToken);
        headers.set("Content-Type", "application/json");
        HttpEntity<EntityEntry> entityEntry = new HttpEntity<>(entry, headers);

        URI url = null;
        try {
            url = new URIBuilder(apiAiUrl + apiAiEntitiesUrl + "/" + entity.getName() + apiAiEntriesUrl)
                    .addParameter("v", apiAiVersionParameter)
                    .build();
        } catch (URISyntaxException e) {
            log.error(e.getStackTrace(), e);
        }
        try {
            restTemplate.exchange(url, HttpMethod.PUT, entityEntry, String.class);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        }
    }

    @Override
    public Entity getEntityByName(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiAiDeveloperToken);
        headers.set("Content-Type", "application/json");
        URI url = null;
        try {
            url = new URIBuilder(apiAiUrl + apiAiEntitiesUrl + "/" + name)
                    .addParameter("v", apiAiVersionParameter)
                    .build();
        } catch (URISyntaxException e) {
            log.error(e.getStackTrace(), e);
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Entity> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Entity.class);
        if (exchange.getStatusCode() == HttpStatus.OK) {
            return exchange.getBody();
        } else {
            return null;
        }
    }
}