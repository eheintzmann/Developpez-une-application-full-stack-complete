package com.openclassrooms.mddapi.controller;

import java.security.Principal;
import java.util.List;

import com.openclassrooms.mddapi.model.dto.TopicDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.service.ITopicService;

@Slf4j
@RestController
@RequestMapping("/api/topics")
public class TopicController {
	
	private final ITopicService topicService;

	public TopicController(ITopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping
	public List<TopicDTO> getTopics(Principal principal) {
		return topicService.getTopics(principal);
	}
	
	
}
