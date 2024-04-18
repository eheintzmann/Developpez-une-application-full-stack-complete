package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionsDTO;

import java.security.Principal;

public interface ISubscriptionService {

    SubscriptionsDTO getSubscriptions(Principal principal);

    SubscriptionsDTO subscribeTo(Long topicId, Principal principal);

    SubscriptionsDTO deleteSubscription(Long topicId, Principal principal);

}
