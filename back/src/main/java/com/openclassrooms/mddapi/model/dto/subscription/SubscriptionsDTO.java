package com.openclassrooms.mddapi.model.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * User Feed DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionsDTO {

        private List<SubscriptionDTO> feed;
}
