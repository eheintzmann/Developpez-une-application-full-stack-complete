package com.openclassrooms.mddapi.model.payload.request.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * Comment Request
 */
@Builder
@Data
public class CommentRequest {

    @NotBlank(message = "content is required")
    private String content;

    @NotNull(message = "post_id is required")
    @Min(value = 0, message = "post_id should be positive")
    @JsonProperty(value = "post_id")
    private Long postId;
}
