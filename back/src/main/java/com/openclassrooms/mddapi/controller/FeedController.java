package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.dto.feed.FeedDTO;
import com.openclassrooms.mddapi.service.IFeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/feed")
public class FeedController {

	private final IFeedService feedService;

	public FeedController(IFeedService feedService) {
		this.feedService = feedService;
	}

	@GetMapping
	public ResponseEntity<FeedDTO> getTopics(Principal principal) {
		return ResponseEntity.ok(feedService.getFeed(principal));
	}

}
