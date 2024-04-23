package com.openclassrooms.mddapi.model.payload.response.post;

import com.openclassrooms.mddapi.model.dto.post.PostDTO;
import com.openclassrooms.mddapi.model.payload.response.IResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostsResponse implements IResponse {

	private List<PostDTO> feed;

}
