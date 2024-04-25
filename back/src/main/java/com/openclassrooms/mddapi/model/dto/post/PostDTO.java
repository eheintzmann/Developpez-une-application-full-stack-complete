package com.openclassrooms.mddapi.model.dto.post;

import lombok.*;

import java.time.Instant;

@Data
@Builder
public class PostDTO {

	private String topic;

	private String author;

	private Long id;

	private String title;

	private String content;

	private Instant updatedAt;

}
