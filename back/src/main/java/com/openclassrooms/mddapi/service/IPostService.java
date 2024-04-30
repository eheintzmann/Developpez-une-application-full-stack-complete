package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.post.PostDTO;
import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IPostService {

    /**
     *
     * @param userDetails User details
     * @return PostsDTO
     */
    List<PostDTO> getPosts(UserDetails userDetails, Sort.Direction sortDirection);

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
