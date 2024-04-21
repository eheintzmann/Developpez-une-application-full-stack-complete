package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.topic.NonExistingTopicException;
import com.openclassrooms.mddapi.exception.user.NonExistingUserException;
import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionsDTO;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.security.Principal;

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
	public SubscriptionsDTO getSubscriptions(Principal principal) {

		User user = this.getUserEntity(principal);

		return conversionService.convert(user, SubscriptionsDTO.class);
	}

	@Override
	public SubscriptionsDTO subscribeTo(Long topicId, Principal principal) {

		User user = this.getUserEntity(principal);
		Topic topic = this.getTopicEntity(topicId);

		if (!user.getSubscriptions().contains(topic)) {
			user.getSubscriptions().add(topic);
		}
		userRepository.saveAndFlush(user);

		return this.getSubscriptions(principal);
	}

	@Override
	public SubscriptionsDTO deleteSubscription(Long topicId, Principal principal) {

		User user = this.getUserEntity(principal);
		Topic topic = this.getTopicEntity(topicId);

		user.getSubscriptions().remove(topic);

		userRepository.saveAndFlush(user);

		return this.getSubscriptions(principal);
	}

	private User getUserEntity(Principal principal) {
		return userRepository
				.findByEmail(principal.getName())
				.orElseThrow(() -> new NonExistingUserException("Cannot find user " + principal.getName()));
	}

	private Topic getTopicEntity(Long topicId) {
		return topicRepository
				.findById(topicId)
				.orElseThrow(() -> new NonExistingTopicException("Cannot find topic " + topicId));
	}

}
