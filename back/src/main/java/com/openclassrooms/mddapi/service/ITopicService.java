package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.model.dto.topic.TopicDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITopicService {

	/**
	 *
	 * @param user User
	 * @return Topics DTO
	 */
	List<TopicDTO> getTopics(UserDetails userDetails);

}
