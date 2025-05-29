package com.example.demo.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	public static final String SECRET_KEY="bfdbdsfbdf7435bfbhdsf33247324jf7r478r4378584";
	
	public String generateToken(String userName) {
		Map<String,Object> claims=new HashMap<>();
		long currentTime=System.currentTimeMillis();
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(currentTime))
				.setExpiration(new Date(currentTime+1000*60))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public void validateToken(String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey())
		.build().parseClaimsJwt(token);
	}
	
	
	private Key getSignKey() {
		byte[] keyByteCoder=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByteCoder);
	}
	

}
