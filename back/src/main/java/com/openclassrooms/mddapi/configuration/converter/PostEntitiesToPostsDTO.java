package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.post.PostDTO;
import com.openclassrooms.mddapi.model.payload.response.post.PostsResponse;
import com.openclassrooms.mddapi.model.entity.Post;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert Post To PostDTO
 */
public class PostEntitiesToPostsDTO implements Converter<List<Post>, PostsResponse> {

    /**
     * Convert Post to PostDTO
     *
     * @param posts Posts
     * @return PostsDTO
     */
    @Override
    public PostsResponse convert(List<Post> posts) {
        List<PostDTO> feed = new ArrayList<>();
        posts.forEach(post -> feed.add(PostDTO.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getAuthor().getUsername())
                        .topic(post.getTopic().getTitle())
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
