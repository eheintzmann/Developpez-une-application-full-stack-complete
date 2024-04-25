package com.openclassrooms.mddapi.model.dto.post;

import com.openclassrooms.mddapi.model.dto.comment.CommentDTO;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.*;

@Data
@Builder
public class PostWithCommentsDTO {

	private Long id;

	private String title;

	private String content;

	private List<CommentDTO> comments;

	private String topic;

	private String author;

	private Instant updatedAt;

}
