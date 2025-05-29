package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.TokenRefreshRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.IAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IAuthService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("registrationUser")
	public String userRegistration(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}

	@PostMapping("/generateToken")
	public AuthResponse generateToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
		AuthResponse authResponse = new AuthResponse();
		if (authentication.isAuthenticated()) {
			String accessToken = service.generateAccessToken(authRequest.getName());
			String refreshToken = service.generateRefreshToken(authRequest.getName());
			authResponse.setAccessToken(accessToken);
			authResponse.setRefreshToken(refreshToken);
			authResponse.setMessage("success generated token");
		} else {
			authResponse.setMessage("fail generated token");
		}
		return authResponse;

	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
		if (!service.validateToken(request.getRefreshToken())) {
			return ResponseEntity.badRequest().body("Invalid refresh token");
		}

		String username = service.getUsernameFromToken(request.getRefreshToken());
		String accessToken = service.generateAccessToken(username);
		String refreshToken = service.generateRefreshToken(username);

		return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken, "Succssfully generated refresh token"));
	}

	@GetMapping("/validateToken")
	public String validateToken(@RequestParam("token") String token) {
		boolean validate =service.validateToken(token);
		return "Validate Token"+validate;
	}

}
