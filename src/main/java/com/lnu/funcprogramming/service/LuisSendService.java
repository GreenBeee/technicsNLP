package com.lnu.funcprogramming.service;

import com.lnu.funcprogramming.model.luis.LuisResponse;

/**
 * @author Oleh Marych
 */
public interface LuisSendService {

    LuisResponse sendLuisRequest(String text);

    String pickAnswer(LuisResponse response);
}
