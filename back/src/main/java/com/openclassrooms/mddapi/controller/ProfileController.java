package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.request.user.ProfileRequest;
import com.openclassrooms.mddapi.model.payload.response.user.ProfileIResponse;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class ProfileController {


	private final UserService userService;
	private final ConversionService conversionService;

	public ProfileController(UserService userService, ConversionService conversionService) {
		this.userService = userService;
		this.conversionService = conversionService;
	}

	@GetMapping
	public ResponseEntity<ProfileIResponse> getUser(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(
				conversionService.convert(
						userService.getProfile(userDetails),
						ProfileIResponse.class
				)
		);
	}

	@PatchMapping
	public ResponseEntity<ProfileIResponse> patchUser(
			@Valid @RequestBody ProfileRequest profileRequest,
			@AuthenticationPrincipal UserDetails userDetails
	) {
		return ResponseEntity.ok(
				conversionService.convert(
						userService.updateProfile(
								userDetails,
								profileRequest.getUsername(),
								profileRequest.getEmail(),
								profileRequest.getPassword()
						),
						ProfileIResponse.class
				)
		);
	}
	
}
