package com.openclassrooms.mddapi.model.dto.post;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
@Builder
public class PostsDTO {

	private SortedSet<PostDTO> feed = new TreeSet<>(Comparator.comparing(PostDTO::getUpdatedAt));

}
