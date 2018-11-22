package com.lnu.funcprogramming.model.apiai;

import lombok.Data;

@Data
public class IntentUserSaysData {
    private String alias;
    private String meta;
    private String text;
    private Boolean userDefined;
}
