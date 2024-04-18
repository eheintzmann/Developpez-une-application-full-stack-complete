package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionDTO;
import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionsDTO;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;

/**
 * Convert User to Profile DTO
 */
public class UserEntityToSubscriptionsDTO implements Converter<User, SubscriptionsDTO> {

    /**
     * Convert User to Profile DTO
     *
     * @param user User
     * @return ProfileDRO
     */
    @Override
    public SubscriptionsDTO convert(User user) {

        SubscriptionsDTO subscriptionsDTO = SubscriptionsDTO.builder()
                .feed(new ArrayList<>())
                .build();

        for (Topic subscription : user.getSubscriptions()) {
            if (subscriptionsDTO != null) {
                subscriptionsDTO.getFeed().add(
                        SubscriptionDTO.builder()
                                .id(subscription.getId())
                                .title(subscription.getTitle())
                                .description(subscription.getDescription())
                                .build());
            }
        }
        return subscriptionsDTO;
    }

}
