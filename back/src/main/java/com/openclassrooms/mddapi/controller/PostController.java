package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.response.post.PostsResponse;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.service.IPostService;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping
	public ResponseEntity<PostsResponse> getPosts(
			@AuthenticationPrincipal User user,
			@RequestParam(defaultValue = "desc") @Pattern(
					regexp = "^(asc|desc)$",
					flags = Pattern.Flag.CASE_INSENSITIVE
			) String order
	) {
		return ResponseEntity.ok(
				conversionService.convert(
						postService.getPosts(user, Sort.Direction.fromString(order)),
						PostsResponse.class
				)
		);
	}

}
