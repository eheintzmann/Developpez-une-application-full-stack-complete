package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.response.subscription.SubscriptionsResponse;
import com.openclassrooms.mddapi.service.ISubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
	public ResponseEntity<SubscriptionsResponse> getSubscriptions(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.getSubscriptions(userDetails))
				.build()
		);
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<SubscriptionsResponse> postSubscription(
			@PathVariable Long id,
			@AuthenticationPrincipal UserDetails userDetails
	) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.subscribeTo(id, userDetails))
				.build()
		);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<SubscriptionsResponse> deleteSubscription(
			@PathVariable Long id,
			@AuthenticationPrincipal UserDetails userDetails
	) {
		return ResponseEntity.ok(SubscriptionsResponse
				.builder()
				.subscriptions(subscriptionService.deleteSubscription(id, userDetails))
				.build()
		);
	}

}
