package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.topic.NonExistingTopicException;
import com.openclassrooms.mddapi.exception.user.NonExistingUserException;
import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionDTO;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {

	private final UserRepository userRepository;
	private final ConversionService conversionService;
	private final TopicRepository topicRepository;

	public SubscriptionService(
			UserRepository userRepository,
			ConversionService conversionService,
			TopicRepository topicRepository
	) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
		this.topicRepository = topicRepository;
	}

	@Override
	public List<SubscriptionDTO> getSubscriptions(User user) {
		List<SubscriptionDTO> subscriptionsDTO = new ArrayList<>();
		userRepository.findSubscriptionsByUser(user.getId()).forEach(
				subscription -> subscriptionsDTO.add(conversionService.convert(subscription, SubscriptionDTO.class))
		);
		return subscriptionsDTO;
	}

	@Override
	public List<SubscriptionDTO> subscribeTo(Long topicId, User user) {
		User userWithSubscriptions = this.getUserWithSubscriptions(user.getId());
		Topic topic = this.getTopicEntity(topicId);

		if (!userWithSubscriptions.getSubscriptions().contains(topic)) {
			userWithSubscriptions.getSubscriptions().add(topic);
		}
		userRepository.saveAndFlush(userWithSubscriptions);

		return this.getSubscriptions(userWithSubscriptions);
	}

	@Override
	public List<SubscriptionDTO> deleteSubscription(Long topicId, User user) {
		User userWithSubscriptions = this.getUserWithSubscriptions(user.getId());
		Topic topic = this.getTopicEntity(topicId);

		userWithSubscriptions.getSubscriptions().remove(topic);

		userRepository.saveAndFlush(userWithSubscriptions);

		return this.getSubscriptions(userWithSubscriptions);
	}

	private Topic getTopicEntity(Long topicId) {
		return topicRepository
				.findById(topicId)
				.orElseThrow(() -> new NonExistingTopicException("Cannot find topic " + topicId));
	}

	private User getUserWithSubscriptions(Long id) {
		return userRepository
				.findUsersByIdWithSubscriptions(id)
				.orElseThrow(() -> new NonExistingUserException("Cannot find user " + id));
	}

}
