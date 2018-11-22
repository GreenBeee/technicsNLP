package com.lnu.funcprogramming.model.apiai;

import lombok.Data;

import java.util.List;

@Data
public class IntentUserSays {
    private Integer count;
    private List<IntentUserSaysData> data;
    private String id;
    private Boolean isTemplate;
    private Integer updated;
}
