package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.response.post.PostsResponse;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.service.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	private final IPostService postService;

	public PostController(IPostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public ResponseEntity<PostsResponse> getPosts(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(postService.getPosts(user));
	}

}
