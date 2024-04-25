package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.comment.CommentDTO;
import com.openclassrooms.mddapi.model.entity.Comment;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert Post To PostDTO
 */
public class CommentEntityToCommentDTO implements Converter<Comment, CommentDTO> {

    /**
     * Convert Post to PostDTO
     *
     * @param comment {@link Comment}
     * @return  CommentDTO
     */
    @Override
    public CommentDTO convert(Comment comment) {

        return CommentDTO.builder()
                .id(comment.getId())
                .author(comment.getAuthor().getUsername())
                .updatedAt(comment.getUpdatedAt())
                .content(comment.getContent())
                .build();
    }

}
