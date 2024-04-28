package com.openclassrooms.mddapi.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface ICommentService {

    /**
     *
     * @param content Comment content
     * @param postId  Post id
     * @param userDetails User details
     */
    void createComment(String content, Long postId, UserDetails userDetails);
}
