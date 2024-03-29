package com.bookstore.bookexplorer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {

    @JsonProperty("userId")
    @Getter
    @Setter
    private Long postUserId;

    @JsonProperty("id")
    @Getter
    @Setter
    private Long postId;

    @JsonProperty("title")
    @Getter
    @Setter
    private String postTitle;

    @JsonProperty("body")
    @Getter
    @Setter
    private String postBody;

    protected Post() {} // check if needed

}
