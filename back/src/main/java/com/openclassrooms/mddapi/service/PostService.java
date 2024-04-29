package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.post.NonExistingPostException;
import com.openclassrooms.mddapi.exception.topic.NonExistingTopicException;
import com.openclassrooms.mddapi.exception.topic.NotUniquePostTitleException;
import com.openclassrooms.mddapi.exception.user.NonExistingUserException;
import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostService implements IPostService {
	private final PostRepository postRepository;
	private final ConversionService conversionService;
	private final TopicRepository topicRepository;
	private final UserRepository userRepository;

	public PostService(
			PostRepository postRepository,
			ConversionService conversionService,
			TopicRepository topicRepository,
			UserRepository userRepository
	) {
		this.postRepository = postRepository;
		this.conversionService = conversionService;
		this.topicRepository = topicRepository;
		this.userRepository = userRepository;
	}


	@Override
	public Iterable<Post> getPosts(UserDetails userDetails, Sort.Direction sortDirection) {

		return this.postRepository.findPostsByUsersId(
				Long.parseLong(userDetails.getUsername()),
				Sort.by(sortDirection, "p.updatedAt")
		);
	}

	@Override
	public PostWithCommentsDTO getPost(Long id) {
		return conversionService.convert(
				postRepository
						.findPostByIdWithTopicWithAuthorWithComments(id)
						.orElseThrow(
								() -> new NonExistingPostException("Cannot find post " + id)
						),
				PostWithCommentsDTO.class
		);
	}

	@Override
	public PostWithCommentsDTO createPost(UserDetails userDetails, String title, String content, Long topicId) {

		User user = userRepository
				.findById(
						Long.parseLong(userDetails.getUsername())
				)
				.orElseThrow(
						() -> new NonExistingUserException("cannot find user "  + userDetails.getUsername())
				);

		Topic topic = topicRepository
				.findById(topicId)
				.orElseThrow(
						() -> new NonExistingTopicException("Cannot find topic " + topicId)
				);

		Post post = Post
				.builder()
				.title(title)
				.content(content)
				.comments(new ArrayList<>())
				.topic(topic)
				.author(user)
				.build();

		try {
			postRepository.save(post);
		} catch (DataIntegrityViolationException ex) {

			if (postRepository.existsByTitle(title)) {
				throw new NotUniquePostTitleException("title " + title + " already used");
			}

			throw ex;
		}

		return conversionService.convert(post, PostWithCommentsDTO.class);
	}

}
