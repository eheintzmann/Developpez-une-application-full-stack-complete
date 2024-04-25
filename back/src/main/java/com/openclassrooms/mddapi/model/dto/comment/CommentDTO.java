package com.openclassrooms.mddapi.model.dto.comment;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CommentDTO {

    private Long id;

    private String author;

    private String content;

    private Instant updatedAt;
}
