package com.openclassrooms.mddapi.model.payload.response.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mddapi.model.dto.comment.CommentDTO;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class PostWithCommentsResponse {

	private Long id;

	private String topic;

	private String author;

	private String title;

	private String content;

	private List<CommentDTO> comments;

	@JsonProperty(value = "updated_at")
	private Instant updatedAt;

}
