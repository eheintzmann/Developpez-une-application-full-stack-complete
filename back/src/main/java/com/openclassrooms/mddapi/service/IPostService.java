package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import com.openclassrooms.mddapi.model.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;

public interface IPostService {

    /**
     *
     * @param userDetails User details
     * @return PostsDTO
     */
    Iterable<Post> getPosts(UserDetails userDetails, Sort.Direction sortDirection);

    /**
     *
     * @param id user id
     * @return Post
     */
    PostWithCommentsDTO getPost(Long id);

    /**
     *
     * @param userDetails User details
     * @param title Post title
     * @param content Post content
     * @param topicId Topic id
     * @return PostWithCommentsDTO
     */
    PostWithCommentsDTO createPost(UserDetails userDetails, String title, String content, Long topicId);
}
