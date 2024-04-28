package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ISubscriptionService {

    /**
     *
     * @param userDetails User details
     * @return SubscriptionsDTO
     */
    List<SubscriptionDTO> getSubscriptions(UserDetails userDetails);

    /**
     *
     * @param topicId Topic id
     * @param userDetails User details
     * @return SubscriptionsDTO
     */
    List<SubscriptionDTO> subscribeTo(Long topicId, UserDetails userDetails);

    /**
     *
     * @param topicId Topic id
     * @param userDetails User details
     * @return SubscriptionsDTO
     */
    List<SubscriptionDTO> deleteSubscription(Long topicId, UserDetails userDetails);

}
