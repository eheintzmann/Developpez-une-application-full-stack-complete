package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
	private final PostRepository postRepository;

	public PostService(
			PostRepository postRepository
	) {
		this.postRepository = postRepository;
	}


	@Override
	public Iterable<Post> getPosts(User user, Sort.Direction sortDirection) {

		return this.postRepository.findPostsByUsersId(
				user.getId(),
				Sort.by(sortDirection, "p.updatedAt")
		);
	}

}
