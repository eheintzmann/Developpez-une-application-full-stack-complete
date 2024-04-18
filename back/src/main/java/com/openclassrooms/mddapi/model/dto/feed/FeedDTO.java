package com.openclassrooms.mddapi.model.dto.feed;

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
public class FeedDTO {

        private List<SubscriptionDTO> feed;
}
