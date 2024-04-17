package com.openclassrooms.mddapi.service;

import java.security.Principal;
import java.util.List;

import com.openclassrooms.mddapi.model.dto.TopicDTO;

public interface ITopicService {

	List<TopicDTO> getTopics(Principal principal);

}
