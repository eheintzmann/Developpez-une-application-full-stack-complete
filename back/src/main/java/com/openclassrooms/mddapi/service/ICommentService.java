package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.entity.User;

public interface ICommentService {

    void createComment(String content, Long postId, User user);
}
