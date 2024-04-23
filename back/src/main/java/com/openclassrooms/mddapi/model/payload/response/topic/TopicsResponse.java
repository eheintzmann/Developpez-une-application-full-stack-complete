package com.openclassrooms.mddapi.model.payload.response.topic;

import com.openclassrooms.mddapi.model.dto.topic.TopicDTO;
import com.openclassrooms.mddapi.model.payload.response.IResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Topics DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicsResponse implements IResponse {

    private List<TopicDTO> topics;

}
