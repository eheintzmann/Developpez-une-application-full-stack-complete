package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.NotExistingUserException;
import com.openclassrooms.mddapi.model.dto.feed.FeedDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class FeedService implements IFeedService {

	private UserRepository userRepository;
	private ConversionService conversionService;

	public FeedService(
			UserRepository userRepository,
			ConversionService conversionService
	) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
	}

	@Override
	public FeedDTO getFeed(Principal principal) {
		User user = userRepository
				.findByEmail(principal.getName())
				.orElseThrow(() -> new NotExistingUserException("Cannot find user {}" + principal.getName()));

		return conversionService.convert(user, FeedDTO.class);	}
}
