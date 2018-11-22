package com.lnu.funcprogramming.controller;

import ai.api.model.AIResponse;
import com.lnu.funcprogramming.service.ApiAiSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Oleh Marych
 */
@RestController
@RequestMapping("/nlp")
@RequiredArgsConstructor
public class NLPController {

    private final ApiAiSendService apiAiSendService;

    @GetMapping("/dialogflow")
    public String dialogflowSendRequest(@RequestParam("text") String text,
                                        HttpSession httpSession) {

        AIResponse response = apiAiSendService.sendApiAiRequest(httpSession.getId(), text);

        return response.getResult().getFulfillment().getSpeech();
    }
}
