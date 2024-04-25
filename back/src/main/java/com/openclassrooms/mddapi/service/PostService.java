package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.post.NonExistingPostException;
import com.openclassrooms.mddapi.model.dto.post.PostWithCommentsDTO;
import com.openclassrooms.mddapi.model.entity.Post;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
	private final PostRepository postRepository;
	private final ConversionService conversionService;

	public PostService(
			PostRepository postRepository,
            @Qualifier("conversionService") ConversionService conversionService) {
		this.postRepository = postRepository;
		this.conversionService = conversionService;
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

}
