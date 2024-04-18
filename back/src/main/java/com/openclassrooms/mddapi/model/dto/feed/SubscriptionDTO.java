package com.openclassrooms.mddapi.model.dto.feed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Profile Post DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDTO {
    private Long id;

    private String title;

    private String description;

}
