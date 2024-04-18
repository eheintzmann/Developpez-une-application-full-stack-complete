package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.dto.subscription.SubscriptionsDTO;
import com.openclassrooms.mddapi.service.ISubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

	private final ISubscriptionService subscriptionService;

	public SubscriptionController(ISubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@GetMapping
	public ResponseEntity<SubscriptionsDTO> getSubscriptions(Principal principal) {
		return ResponseEntity.ok(subscriptionService.getSubscriptions(principal));
	}

	@PostMapping(path = "/{id}")
	public ResponseEntity<SubscriptionsDTO> postSubscription(@PathVariable Long id,  Principal principal) {
		return ResponseEntity.ok(subscriptionService.subscribeTo(id, principal));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<SubscriptionsDTO> deleteSubscription(@PathVariable Long id, Principal principal) {

		return ResponseEntity.ok(subscriptionService.deleteSubscription(id, principal));
	}

}
