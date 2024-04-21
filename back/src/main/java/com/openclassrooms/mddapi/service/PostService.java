package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.user.NonExistingUserException;
import com.openclassrooms.mddapi.model.dto.post.PostDTO;
import com.openclassrooms.mddapi.model.dto.post.PostsDTO;
import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class PostService implements IPostService {

	private final UserRepository userRepository;
	private final ConversionService conversionService;

	public PostService(
			UserRepository userRepository,
			ConversionService conversionService
	) {
		this.userRepository = userRepository;
		this.conversionService = conversionService;
	}


	@Override
	public PostsDTO getPosts(Principal principal) {
		User user = getUserEntity(principal);

		SortedSet<PostDTO> sortedPosts = new TreeSet<>(Comparator.comparing(PostDTO::getUpdatedAt));
		for (Topic subscription : user.getSubscriptions()) {
			for(Post post : subscription.getPosts()) {
				sortedPosts.add(conversionService.convert(post, PostDTO.class));
			}
		}
		return PostsDTO
				.builder()
				.feed(sortedPosts)
				.build();
	}


	private User getUserEntity(Principal principal) {
		return userRepository
				.findByEmail(principal.getName())
				.orElseThrow(() -> new NonExistingUserException("Cannot find user " + principal.getName()));
	}

}
