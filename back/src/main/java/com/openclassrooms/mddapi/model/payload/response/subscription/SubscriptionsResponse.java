package com.openclassrooms.mddapi.model.payload.response.subscription;

import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionDTO;
import com.openclassrooms.mddapi.model.payload.response.IResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Subscriptions DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionsResponse implements IResponse {

        private List<SubscriptionDTO> subscriptions = new ArrayList<>();
}
