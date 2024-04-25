package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.model.payload.request.comment.CommentRequest;
import com.openclassrooms.mddapi.service.ICommentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

	private final ICommentService commentService;

	public CommentController(ICommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	public  void postPost(
			@AuthenticationPrincipal User user,
			@Valid @RequestBody CommentRequest req
	) {
		commentService.createComment(req.getContent(), req.getPostId(), user);
	}

}
