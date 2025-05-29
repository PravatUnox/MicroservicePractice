package com.example.demo.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.AuthConfig;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {
	private final UserInfoRepository repo;
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	public AuthServiceImpl(UserInfoRepository repo,PasswordEncoder passwordEncoder,JwtService jwtService) {
		this.repo=repo;
		this.passwordEncoder=passwordEncoder;
		this.jwtService=jwtService;
	}
	
	@Override
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repo.save(userInfo);
		return "User Save Successfuly!!!!";
	}
	
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
	
	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
	}

}
