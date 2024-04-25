package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.payload.response.post.PostsResponse;
import com.openclassrooms.mddapi.model.entity.User;
import org.springframework.data.domain.Sort;

public interface IPostService {

    /**
     *
     * @param user User
     * @return PostsDTO
     */
    PostsResponse getPosts(User user, Sort.Direction sortDirection);
}
