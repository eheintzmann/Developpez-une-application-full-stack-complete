package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import com.openclassrooms.mddapi.model.payload.request.post.PostRequest;
import com.openclassrooms.mddapi.model.payload.response.post.PostsResponse;
import com.openclassrooms.mddapi.service.IPostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	private final IPostService postService;
	private final ConversionService conversionService;

	public PostController(IPostService postService, @Qualifier("conversionService") ConversionService conversionService) {
		this.postService = postService;
		this.conversionService = conversionService;
	}

	@GetMapping("/user")
	public ResponseEntity<PostsResponse> getPosts(
			@AuthenticationPrincipal UserDetails userDetails,
			@RequestParam(defaultValue = "desc") @Pattern(
					regexp = "^(asc|desc)$",
					flags = Pattern.Flag.CASE_INSENSITIVE
			) String order
	) {
		return ResponseEntity.ok(
				conversionService.convert(
						postService.getPosts(userDetails, Sort.Direction.fromString(order)),
						PostsResponse.class
				)
		);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<PostWithCommentsDTO> getPost(@PathVariable Long id) {
		return ResponseEntity.ok(postService.getPost(id));
	}

	@PostMapping
	public  ResponseEntity<PostWithCommentsDTO> postPost(
			@AuthenticationPrincipal UserDetails userDetails,
			@Valid @RequestBody PostRequest req
	) {
		return ResponseEntity.ok(
				postService.createPost(userDetails, req.getTitle(), req.getContent(), req.getTopicId())
		);
	}

}
