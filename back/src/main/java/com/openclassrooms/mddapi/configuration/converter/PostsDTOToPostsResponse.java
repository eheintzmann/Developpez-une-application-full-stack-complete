package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.post.PostDTO;
import com.openclassrooms.mddapi.model.payload.response.post.PostResponse;
import com.openclassrooms.mddapi.model.payload.response.post.PostsResponse;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert Post To PostDTO
 */
public class PostsDTOToPostsResponse implements Converter<List<PostDTO>, PostsResponse> {

    /**
     * Convert Post to PostDTO
     *
     * @param posts Posts
     * @return PostsDTO
     */
    @Override
    public PostsResponse convert(List<PostDTO> posts) {
        List<PostResponse> feed = new ArrayList<>();
        posts.forEach(post -> feed.add(PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getAuthor())
                        .topic(post.getTopic())
                        .updatedAt(post.getUpdatedAt())
                        .build()
                )
        );
        return PostsResponse
                .builder()
                .feed(feed)
                .build();
    }

}
