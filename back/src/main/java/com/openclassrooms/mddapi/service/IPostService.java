package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.post.PostsDTO;

import java.security.Principal;

public interface IPostService {

    PostsDTO getPosts(Principal principal);
}
