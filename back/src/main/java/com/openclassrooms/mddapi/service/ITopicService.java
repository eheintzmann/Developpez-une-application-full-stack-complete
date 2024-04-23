package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.model.dto.topic.TopicDTO;
import com.openclassrooms.mddapi.model.entity.User;

public interface ITopicService {

	/**
	 *
	 * @param user User
	 * @return Topics DTO
	 */
	List<TopicDTO> getTopics(User user);

}
