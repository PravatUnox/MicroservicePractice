package com.example.demo.service;

import com.example.demo.entity.UserInfo;

public interface IAuthService {

	public String addUser(UserInfo userInfo);
	
	public boolean validateToken(String token);
	public String generateToken(String userName);
	
	public String generateAccessToken(String userName);
	public String generateRefreshToken(String userName);
	
	public String getUsernameFromToken(String token);
	
}
