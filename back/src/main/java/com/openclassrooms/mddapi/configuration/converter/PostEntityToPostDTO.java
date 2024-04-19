package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.post.PostDTO;
import com.openclassrooms.mddapi.model.entity.Post;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert Post To PostDTO
 */
public class PostEntityToPostDTO implements Converter<Post, PostDTO> {

    /**
     * Convert Post to PostDTO
     *
     * @param post Post
     * @return PostDTO
     */
    @Override
    public PostDTO convert(Post post) {

        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor().getUsername())
                .topic(post.getTopic().getTitle())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

}
