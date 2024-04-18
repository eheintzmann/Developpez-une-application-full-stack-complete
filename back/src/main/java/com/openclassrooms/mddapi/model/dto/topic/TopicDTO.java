package com.openclassrooms.mddapi.model.dto.topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Topic DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicDTO {

    private Long id;

    private String title;

    private String description;

    private boolean isSubscribed;

}
