package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.post.NonExistingPostException;
import com.openclassrooms.mddapi.exception.user.NonExistingUserException;
import com.openclassrooms.mddapi.model.entity.Comment;
import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService{


    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String content, Long postId, UserDetails userDetails) {
        User user = userRepository
                .findById(
                        Long.parseLong(userDetails.getUsername())
                )
                .orElseThrow(
                        () -> new NonExistingUserException("cannot find user "  + userDetails.getUsername())
                );

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
