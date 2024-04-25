package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.post.NonExistingPostException;
import com.openclassrooms.mddapi.model.entity.Comment;
import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService{


    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(String content, Long postId, User user) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new NonExistingPostException("Cannot find post " + postId));

        Comment comment = Comment
                .builder()
                .author(user)
                .content(content)
                .post(post)
                .build();

        commentRepository.saveAndFlush(comment);
    }
}
