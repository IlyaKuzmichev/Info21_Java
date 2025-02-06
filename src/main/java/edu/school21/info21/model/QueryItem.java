package edu.school21.info21.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class QueryItem {
    @JsonProperty("name")
    private String name;

    @JsonProperty("query")
    private String query;

    @JsonProperty("description")
    private String description;
}

