package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.model.payload.response.topic.TopicsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.service.ITopicService;

@Slf4j
@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

	private final ITopicService topicService;

	public TopicController(ITopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping
	public ResponseEntity<TopicsResponse> getTopics(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(TopicsResponse
				.builder()
				.topics(topicService.getTopics(user))
				.build()
		);

	}

}
