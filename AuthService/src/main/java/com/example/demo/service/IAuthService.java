package com.example.demo.service;

import com.example.demo.entity.UserInfo;

public interface IAuthService {

	public String addUser(UserInfo userInfo);
	
	public void validateToken(String token);
	public String generateToken(String userName);
}
