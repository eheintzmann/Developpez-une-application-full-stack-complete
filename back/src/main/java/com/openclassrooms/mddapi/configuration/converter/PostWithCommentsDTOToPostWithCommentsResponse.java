package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import com.openclassrooms.mddapi.model.payload.response.post.PostWithCommentsResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert Post To PostDTO
 */
public class PostWithCommentsDTOToPostWithCommentsResponse implements Converter<PostWithCommentsDTO, PostWithCommentsResponse> {

    /**
     * Convert Post to PostDTO
     *
     * @param post Post
     * @return PostDTO
     */
    @Override
    public PostWithCommentsResponse convert(PostWithCommentsDTO post) {

        return PostWithCommentsResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .topic(post.getTopic())
                .updatedAt(post.getUpdatedAt())
                .comments(post.getComments())
                .build();
    }

}
