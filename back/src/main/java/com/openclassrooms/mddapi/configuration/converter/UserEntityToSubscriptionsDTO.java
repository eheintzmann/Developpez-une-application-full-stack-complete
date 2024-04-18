package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionDTO;
import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionsDTO;
import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.util.Comparator;
import java.util.TreeSet;

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
                .subscriptions(new TreeSet<>(Comparator.comparing(SubscriptionDTO::getTitle)))
                .build();

        for (Topic subscription : user.getSubscriptions()) {
            if (subscriptionsDTO != null) {
                subscriptionsDTO.getSubscriptions().add(
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
