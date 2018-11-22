package com.lnu.funcprogramming.model.luis;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Luis response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "query",
    "topScoringIntent",
    "intents",
    "entities"
})
public class LuisResponse {

  @JsonProperty("query")
  private String query;
  @JsonProperty("topScoringIntent")
  private TopScoringIntent topScoringIntent;
  @JsonProperty("intents")
  private List<Intent> intents = null;
  @JsonProperty("entities")
  private List<Entity> entities = null;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * Gets query.
   *
   * @return the query
   */
  @JsonProperty("query")
  public String getQuery() {
    return query;
  }

  /**
   * Sets query.
   *
   * @param query the query
   */
  @JsonProperty("query")
  public void setQuery(String query) {
    this.query = query;
  }

  /**
   * Gets top scoring intent.
   *
   * @return the top scoring intent
   */
  @JsonProperty("topScoringIntent")
  public TopScoringIntent getTopScoringIntent() {
    return topScoringIntent;
  }

  /**
   * Sets top scoring intent.
   *
   * @param topScoringIntent the top scoring intent
   */
  @JsonProperty("topScoringIntent")
  public void setTopScoringIntent(TopScoringIntent topScoringIntent) {
    this.topScoringIntent = topScoringIntent;
  }

  /**
   * Gets intents.
   *
   * @return the intents
   */
  @JsonProperty("intents")
  public List<Intent> getIntents() {
    return intents;
  }

  /**
   * Sets intents.
   *
   * @param intents the intents
   */
  @JsonProperty("intents")
  public void setIntents(List<Intent> intents) {
    this.intents = intents;
  }

  /**
   * Gets entities.
   *
   * @return the entities
   */
  @JsonProperty("entities")
  public List<Entity> getEntities() {
    return entities;
  }

  /**
   * Sets entities.
   *
   * @param entities the entities
   */
  @JsonProperty("entities")
  public void setEntities(List<Entity> entities) {
    this.entities = entities;
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