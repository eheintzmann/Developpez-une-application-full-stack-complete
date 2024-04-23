package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionDTO;
import com.openclassrooms.mddapi.model.entity.User;

import java.util.List;

public interface ISubscriptionService {

    /**
     *
     * @param user User
     * @return SubscriptionsDTO
     */
    List<SubscriptionDTO> getSubscriptions(User user);

    /**
     *
     * @param topicId Topic id
     * @param user User
     * @return SubscriptionsDTO
     */
    List<SubscriptionDTO> subscribeTo(Long topicId, User user);

    /**
     *
     * @param topicId Topic id
     * @param user User
     * @return SubscriptionsDTO
     */
    List<SubscriptionDTO> deleteSubscription(Long topicId, User user);

}
