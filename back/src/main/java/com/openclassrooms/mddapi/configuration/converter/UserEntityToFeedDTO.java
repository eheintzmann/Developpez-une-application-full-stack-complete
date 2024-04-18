package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.feed.SubscriptionDTO;
import com.openclassrooms.mddapi.model.dto.feed.FeedDTO;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;

/**
 * Convert User to Profile DTO
 */
public class UserEntityToFeedDTO implements Converter<User, FeedDTO> {

    /**
     * Convert User to Profile DTO
     *
     * @param user User
     * @return ProfileDRO
     */
    @Override
    public FeedDTO convert(User user) {

        FeedDTO feedDTO = FeedDTO.builder()
                .feed(new ArrayList<>())
                .build();

        for (Topic subscription : user.getSubscriptions()) {
            if (feedDTO != null) {
                feedDTO.getFeed().add(
                        SubscriptionDTO.builder()
                                .id(subscription.getId())
                                .title(subscription.getTitle())
                                .description(subscription.getDescription())
                                .build());
            }
        }
        return feedDTO;
    }

}
