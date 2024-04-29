package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.response.subscription.SubscriptionsResponse;
import com.openclassrooms.mddapi.model.payload.response.topic.TopicsResponse;
import com.openclassrooms.mddapi.service.ISubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.service.ITopicService;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

	private final ITopicService topicService;
	private final ISubscriptionService subscriptionService;

	public TopicController(
			ITopicService topicService,
			ISubscriptionService subscriptionService
	) {
		this.topicService = topicService;
		this.subscriptionService = subscriptionService;
	}

	@GetMapping
	public ResponseEntity<TopicsResponse> getTopics(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(TopicsResponse
				.builder()
				.topics(topicService.getTopics(userDetails))
				.build()
		);

	}

	@GetMapping(path = "/user")
	public ResponseEntity<SubscriptionsResponse> getSubscriptions(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.getSubscriptions(userDetails))
				.build()
		);
	}

	@PostMapping(path = "/{topicId}/user")
	public ResponseEntity<SubscriptionsResponse> postSubscription(
			@PathVariable Long topicId,
			@AuthenticationPrincipal UserDetails userDetails
	) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.subscribeTo(topicId, userDetails))
				.build()
		);
	}

	@DeleteMapping(path = "/{topicId}/user")
	public ResponseEntity<SubscriptionsResponse> deleteSubscription(
			@PathVariable Long topicId,
			@AuthenticationPrincipal UserDetails userDetails
	) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.deleteSubscription(topicId, userDetails))
				.build()
		);
	}

}
