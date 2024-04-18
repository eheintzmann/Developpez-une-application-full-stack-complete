package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.feed.FeedDTO;

import java.security.Principal;

public interface IFeedService {

    FeedDTO getFeed(Principal principal);

}
