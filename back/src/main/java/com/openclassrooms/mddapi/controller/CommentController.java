package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.request.comment.CommentRequest;
import com.openclassrooms.mddapi.model.payload.response.IResponse;
import com.openclassrooms.mddapi.service.ICommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

	private final ICommentService commentService;

	public CommentController(ICommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	public ResponseEntity<IResponse> postPost(
			@AuthenticationPrincipal UserDetails userDetails,
			@Valid @RequestBody CommentRequest req
	) {
		commentService.createComment(req.getContent(), req.getPostId(), userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
