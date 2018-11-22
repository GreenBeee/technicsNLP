package com.lnu.funcprogramming.service.impl;

import com.lnu.funcprogramming.model.luis.LuisAnswers;
import com.lnu.funcprogramming.model.luis.LuisIntentType;
import com.lnu.funcprogramming.model.luis.LuisResponse;
import com.lnu.funcprogramming.service.LuisSendService;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

/**
 * @author Oleh Marych
 */
@Service
@RequiredArgsConstructor
public class LuisSendServiceImpl implements LuisSendService {

    private final RestTemplate restTemplate;

    @Value("${luis.base.url}")
    private String luisUrl;

    @Override
    public LuisResponse sendLuisRequest(String text) {
        try {
            URIBuilder uriBuilder = new URIBuilder(luisUrl).addParameter("q", text);
            return restTemplate.getForObject(uriBuilder.build(), LuisResponse.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String pickAnswer(LuisResponse response) {
        LuisIntentType intent = LuisIntentType
                .valueOf(response.getTopScoringIntent().getIntent().toUpperCase());

        switch (intent) {
            case INTEL:
                return LuisAnswers.INTEL_ACTION;
            case COMPUTER:
                return LuisAnswers.COMPUTER_ACTION;
        }

        return LuisAnswers.DEFAULT_FALLBACK;
    }
}
