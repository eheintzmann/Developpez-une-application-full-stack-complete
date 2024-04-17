package com.openclassrooms.mddapi.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.mddapi.exception.NotExistingUserException;
import com.openclassrooms.mddapi.model.dto.TopicDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
public class TopicService implements ITopicService {

	private final TopicRepository topicRepository;
	private final ConversionService conversionService;
	private final UserRepository userRepository;

	public TopicService(
			TopicRepository topicRepository,
			ConversionService conversionService,
			UserRepository userRepository) {
		this.topicRepository = topicRepository;
		this.conversionService = conversionService;
		this.userRepository = userRepository;
	}

	@Override
	public List<TopicDTO> getTopics(Principal principal) {

		User user = userRepository.findByEmail(principal.getName())
				.orElseThrow(
						() -> new NotExistingUserException("Cannot find user : " + principal.getName())
				);

		List<TopicDTO> topicDTOs = new ArrayList<>();
		for (Topic topic: topicRepository.findAll()) {
			TopicDTO topicDTO = conversionService.convert(topic, TopicDTO.class);
			if (topicDTO != null && topic.getSubscribers().contains(user)) {
				topicDTO.setSubscribed(true);
			}
			topicDTOs.add(topicDTO);
		}
		return topicDTOs;
	}
	
}
