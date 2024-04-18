package com.openclassrooms.mddapi.model.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Subscriptions DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionsDTO {

        private SortedSet<SubscriptionDTO> subscriptions = new TreeSet<>(Comparator.comparing(SubscriptionDTO::getTitle));
}
