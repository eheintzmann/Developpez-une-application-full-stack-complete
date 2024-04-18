package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.payload.request.user.UserRequest;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {


	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<UserDTO> getUser(Principal principal) {
		return ResponseEntity.ok(userService.getProfile(principal));
	}

	@PutMapping
	public ResponseEntity<UserDTO> putUser(Principal principal, @Valid @RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(
				this.userService.updateProfile(principal, userRequest.getUsername(), userRequest.getEmail())
		);
	}
	
}
