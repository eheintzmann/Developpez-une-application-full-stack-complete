package com.openclassrooms.mddapi.model.payload.request.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * Register Request
 */
@Builder
@Data
public class PostRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @Min(0)
    @JsonProperty(value = "topic_id")
    private Long topicId;
}
