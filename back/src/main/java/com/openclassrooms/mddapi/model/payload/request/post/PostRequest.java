package com.openclassrooms.mddapi.model.payload.request.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * Post Request
 */
@Getter
public class PostRequest {

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "content is required")
    private String content;

    @NotNull(message = "topic_id is required")
    @Min(value = 0, message = "topic_id should be positive")
    @JsonProperty("topic_id")
    private Long topicId;
}
