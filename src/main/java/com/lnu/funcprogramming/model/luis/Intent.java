package com.lnu.funcprogramming.model.luis;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Intent.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "intent",
    "score"
})
public class Intent {

  @JsonProperty("intent")
  private String intent;
  @JsonProperty("score")
  private Double score;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * Gets intent.
   *
   * @return the intent
   */
  @JsonProperty("intent")
  public String getIntent() {
    return intent;
  }

  /**
   * Sets intent.
   *
   * @param intent the intent
   */
  @JsonProperty("intent")
  public void setIntent(String intent) {
    this.intent = intent;
  }

  /**
   * Gets score.
   *
   * @return the score
   */
  @JsonProperty("score")
  public Double getScore() {
    return score;
  }

  /**
   * Sets score.
   *
   * @param score the score
   */
  @JsonProperty("score")
  public void setScore(Double score) {
    this.score = score;
  }

  /**
   * Gets additional properties.
   *
   * @return the additional properties
   */
  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  /**
   * Sets additional property.
   *
   * @param name the name
   * @param value the value
   */
  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}