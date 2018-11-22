package com.lnu.funcprogramming.model.luis;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "entity",
    "type",
    "startIndex",
    "endIndex",
    "score"
})
public class Entity {

  @JsonProperty("entity")
  private String entity;
  @JsonProperty("type")
  private String type;
  @JsonProperty("startIndex")
  private Integer startIndex;
  @JsonProperty("endIndex")
  private Integer endIndex;
  @JsonProperty("score")
  private Double score;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * Gets entity.
   *
   * @return the entity
   */
  @JsonProperty("entity")
  public String getEntity() {
    return entity;
  }

  /**
   * Sets entity.
   *
   * @param entity the entity
   */
  @JsonProperty("entity")
  public void setEntity(String entity) {
    this.entity = entity;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets start index.
   *
   * @return the start index
   */
  @JsonProperty("startIndex")
  public Integer getStartIndex() {
    return startIndex;
  }

  /**
   * Sets start index.
   *
   * @param startIndex the start index
   */
  @JsonProperty("startIndex")
  public void setStartIndex(Integer startIndex) {
    this.startIndex = startIndex;
  }

  /**
   * Gets end index.
   *
   * @return the end index
   */
  @JsonProperty("endIndex")
  public Integer getEndIndex() {
    return endIndex;
  }

  /**
   * Sets end index.
   *
   * @param endIndex the end index
   */
  @JsonProperty("endIndex")
  public void setEndIndex(Integer endIndex) {
    this.endIndex = endIndex;
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
