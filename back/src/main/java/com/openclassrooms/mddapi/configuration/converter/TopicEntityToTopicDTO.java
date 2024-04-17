package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.TopicDTO;
import com.openclassrooms.mddapi.model.entity.Topic;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert Topic to Topic DTO
 */
public class TopicEntityToTopicDTO implements Converter<Topic, TopicDTO> {

    /**
     * Convert Topic to Topic DTO
     *
     * @param topic Topic
     * @return TopicDto
     */
    @Override
    public TopicDTO convert(Topic topic) {

        return TopicDTO.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .isSubscribed(false)
                .build();
    }
}
