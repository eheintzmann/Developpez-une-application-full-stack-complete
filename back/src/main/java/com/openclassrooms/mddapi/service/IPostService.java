package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.User;
import org.springframework.data.domain.Sort;

public interface IPostService {

    /**
     *
     * @param user User
     * @return PostsDTO
     */
    Iterable<Post> getPosts(User user, Sort.Direction sortDirection);

    /**
     *
     * @param id user id
     * @return Post
     */
    PostWithCommentsDTO getPost(Long id);
}
