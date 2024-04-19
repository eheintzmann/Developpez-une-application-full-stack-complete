package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.dto.post.PostsDTO;
import com.openclassrooms.mddapi.service.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostController {

	private final IPostService postService;

	public PostController(IPostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public ResponseEntity<PostsDTO> getPosts(Principal principal) {
		return ResponseEntity.ok(postService.getPosts(principal));
	}

}
