package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.response.subscription.SubscriptionsResponse;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.service.ISubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user/subscriptions")
public class SubscriptionController {

	private final ISubscriptionService subscriptionService;

	public SubscriptionController(ISubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@GetMapping
	public ResponseEntity<SubscriptionsResponse> getSubscriptions(@AuthenticationPrincipal User user) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.getSubscriptions(user))
				.build()
		);
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<SubscriptionsResponse> postSubscription(
			@PathVariable Long id,
			@AuthenticationPrincipal User user
	) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.subscribeTo(id, user))
				.build()
		);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<SubscriptionsResponse> deleteSubscription(
			@PathVariable Long id,
			@AuthenticationPrincipal User user
	) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.deleteSubscription(id, user))
				.build()
		);
	}

}
