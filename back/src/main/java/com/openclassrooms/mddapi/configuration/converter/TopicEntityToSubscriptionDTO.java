package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionDTO;
import com.openclassrooms.mddapi.model.entity.Topic;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert User to Profile DTO
 */
public class TopicEntityToSubscriptionDTO implements Converter<Topic, SubscriptionDTO> {

    /**
     * Convert User to Profile DTO
     *
     * @param topic Topic
     * @return Subscription DTO
     */
    @Override
    public SubscriptionDTO convert(Topic topic) {
        return SubscriptionDTO
                .builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .build();
    }

}
