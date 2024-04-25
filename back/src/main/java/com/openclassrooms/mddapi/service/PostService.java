package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.post.NonExistingPostException;
import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostService implements IPostService {
	private final PostRepository postRepository;
	private final ConversionService conversionService;
	private final TopicRepository topicRepository;

	public PostService(
			PostRepository postRepository,
			@Qualifier("conversionService") ConversionService conversionService, TopicRepository topicRepository) {
		this.postRepository = postRepository;
		this.conversionService = conversionService;
		this.topicRepository = topicRepository;
	}


	@Override
	public Iterable<Post> getPosts(User user, Sort.Direction sortDirection) {

		return this.postRepository.findPostsByUsersId(
				user.getId(),
				Sort.by(sortDirection, "p.updatedAt")
		);
	}

	@Override
	public PostWithCommentsDTO getPost(Long id) {
		return conversionService.convert(
				postRepository.findById(id).orElseThrow(() -> new NonExistingPostException("Cannot find post " + id)),
				PostWithCommentsDTO.class
		);
	}

	@Override
	public PostWithCommentsDTO createPost(User user, String title, String content, Long topicId) {
		Topic topic = topicRepository
				.findById(topicId)
				.orElseThrow(RuntimeException::new);

		Post post = Post
				.builder()
				.title(title)
				.content(content)
				.comments(new ArrayList<>())
				.topic(topic)
				.author(user)
				.build();

		postRepository.saveAndFlush(post);

		return conversionService.convert(post, PostWithCommentsDTO.class);
	}

}
