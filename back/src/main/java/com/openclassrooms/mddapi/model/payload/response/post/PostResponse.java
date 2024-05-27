package com.openclassrooms.mddapi.model.payload.response.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PostResponse {

	private Long id;

	private String topic;

	private String author;

	private String title;

	private String content;

	@JsonProperty(value = "updated_at")
	private Instant updatedAt;

}
