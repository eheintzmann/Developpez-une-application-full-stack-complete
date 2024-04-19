package com.openclassrooms.mddapi.model.dto.post;

import com.openclassrooms.mddapi.model.dto.comment.CommentDTO;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
@Builder
public class PostWithCommentsDTO {

	private Long id;

	private String title;

	private String content;

	private SortedSet<CommentDTO> comments = new TreeSet<>(Comparator.comparing(CommentDTO::getUpdatedAt));

	private String topic;

	private String author;

	private Instant updatedAt;

}
