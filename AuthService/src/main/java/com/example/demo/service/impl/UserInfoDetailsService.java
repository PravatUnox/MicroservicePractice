package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dto.UserInfoDetails;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

public class UserInfoDetailsService implements UserDetailsService{

	@Autowired
	private UserInfoRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo=userRepo.findByName(username);
		return userInfo.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User not found exception"));
		
	}

}
