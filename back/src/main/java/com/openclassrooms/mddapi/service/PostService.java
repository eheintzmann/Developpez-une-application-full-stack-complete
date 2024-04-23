package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.payload.response.post.PostsResponse;
import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
	private final ConversionService conversionService;
	private final PostRepository postRepository;

	public PostService(
			ConversionService conversionService,
			PostRepository postRepository
	) {
		this.conversionService = conversionService;
		this.postRepository = postRepository;
	}


	@Override
	public PostsResponse getPosts(User user) {

		Iterable<Post> posts = this.postRepository.findPostsByUsersId(user.getId());

		return conversionService.convert(posts, PostsResponse.class);
	}

}
