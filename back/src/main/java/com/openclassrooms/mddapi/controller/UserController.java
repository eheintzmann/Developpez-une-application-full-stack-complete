package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.request.user.ProfileRequest;
import com.openclassrooms.mddapi.model.payload.response.user.ProfileIResponse;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {


	private final UserService userService;
	private final ConversionService conversionService;

	public UserController(UserService userService, @Qualifier("conversionService") ConversionService conversionService) {
		this.userService = userService;
		this.conversionService = conversionService;
	}

	@GetMapping
	public ResponseEntity<ProfileIResponse> getUser(Principal principal) {
		return ResponseEntity.ok(
				conversionService.convert(
						userService.getProfile(principal),
						ProfileIResponse.class
				)
		);
	}

	@PutMapping
	public ResponseEntity<ProfileIResponse> putUser(Principal principal, @Valid @RequestBody ProfileRequest profileRequest) {
		return ResponseEntity.ok(
				conversionService.convert(
						userService.updateProfile(principal, profileRequest.getUsername(), profileRequest.getEmail()),
						ProfileIResponse.class
				)
		);
	}
	
}
