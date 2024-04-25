package com.openclassrooms.mddapi.model.payload.request.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * Comment Request
 */
@Builder
@Data
public class CommentRequest {

    @NotEmpty
    private String content;

    @Min(0)
    @JsonProperty(value = "post_id")
    private Long postId;
}
