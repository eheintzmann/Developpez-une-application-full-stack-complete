package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.comment.CommentDTO;
import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import com.openclassrooms.mddapi.model.entity.Comment;
import com.openclassrooms.mddapi.model.entity.Post;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert Post To PostDTO
 */
public class PostEntityToPostWitCommentsDTO implements Converter<Post, PostWithCommentsDTO> {

    /**
     * Convert Post to PostDTO
     *
     * @param post Post
     * @return PostDTO
     */
    @Override
    public PostWithCommentsDTO convert(Post post) {

        return PostWithCommentsDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor().getUsername())
                .topic(post.getTopic().getTitle())
                .updatedAt(post.getUpdatedAt())
                .comments(this.convertComments(post.getComments()))
                .build();
    }

    private CommentDTO convertComment(Comment comment) {
        return CommentDTO
                .builder()
                .id(comment.getId())
                .author(comment.getAuthor().getUsername())
                .updatedAt(comment.getUpdatedAt())
                .content(comment.getContent())
                .build();
    }

    private List<CommentDTO> convertComments (List<Comment> comments) {
        List<CommentDTO> commentsDTO = new ArrayList<>();
        comments.forEach(comment -> commentsDTO.add(this.convertComment(comment)));
        return commentsDTO;
    }

}
