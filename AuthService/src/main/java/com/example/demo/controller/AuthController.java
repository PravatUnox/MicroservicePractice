package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
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
	public String generateToken(@RequestBody  AuthRequest authRequest) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(),authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return service.generateToken(authRequest.getName());
		}else {
			return "Invalide Authentication";
		}
	
	}

	@GetMapping("/validateToken")
	public String validateToken(@RequestParam("token")
			String token) {
		service.validateToken(token);
		return "Validate Token";
	}
	
}
