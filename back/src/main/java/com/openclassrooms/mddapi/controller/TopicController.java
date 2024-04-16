package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.service.ITopicService;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
	
	private final ITopicService topicService;
	
	public TopicController(ITopicService topicService) {
		this.topicService = topicService;		
	}

	@GetMapping
	public List<Topic> getTopics() {
		return topicService.getTopics();
	}
	
	
}
