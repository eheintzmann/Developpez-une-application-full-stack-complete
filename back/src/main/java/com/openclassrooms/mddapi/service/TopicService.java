package com.openclassrooms.mddapi.service;

import java.util.*;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.dto.topic.TopicDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
public class TopicService implements ITopicService {
	private final TopicRepository topicRepository;
	private final ConversionService conversionService;

	public TopicService(
			TopicRepository topicRepository,
			ConversionService conversionService
	) {
		this.topicRepository = topicRepository;
		this.conversionService = conversionService;
	}

	@Override
	public List<TopicDTO> getTopics(User user) {
		List<TopicDTO> topicsDTO = new ArrayList<>();

		Iterable<Topic> topics = topicRepository.findAllTopicsWithSubscribers();

		topics.forEach(topic -> {
			TopicDTO topicDTO = conversionService.convert(topic, TopicDTO.class);
			if (topicDTO != null && topic.getSubscribers().contains(user)) {
				topicDTO.setSubscribed(true);
			}
			topicsDTO.add(topicDTO);

		});
		return topicsDTO;
	}

}
