package com.openclassrooms.mddapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
public class TopicService implements ITopicService {

	private final TopicRepository topicRepository;
	
	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	@Override
	public List<Topic> getTopics() {
		return topicRepository.findAll();
	}
	
}
