package com.openclassrooms.mddapi.service;

import java.security.Principal;
import java.util.SortedSet;

import com.openclassrooms.mddapi.model.dto.topic.TopicDTO;

public interface ITopicService {

	SortedSet<TopicDTO> getTopics(Principal principal);

}
