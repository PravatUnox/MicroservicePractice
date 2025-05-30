package com.practice.ms.filter;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	 public static final long RECOVERY_TIME = 86_400_000;
		public static final long EXPIRATION_TIME = 864_000_000;

		@Value("${app.jwt-secret}")
	    private String jwtSecret;
        //aSdFgHjKlZxCvBnMqWeRtYuIoPaSdFgHjKlZxCvBnMqWeRtYuIoPaSdFgHjKlZCvBnMqWeRtYuIoPaSdFgHjKlZxCvBnMqWeRtYuIoPaSdFgHjKlZ
	    @Value("${app.jwt-expiration-ms}")
	    private int jwtExpirationMs;

	    @Value("${app.refresh-token-expiration-ms}")
	    private int refreshTokenExpirationMs;
	    
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
		
		public String generateRefreshToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationMs))
	                .signWith(getSignKey(),SignatureAlgorithm.HS512)
	                .compact();
	    }
		public String getUsernameFromToken(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }
		
		public boolean validateToken(String token) {
			try {
	            Jwts.parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token);
	            return true;
	        } catch (JwtException e) {
	            return false;
	        }
		}
		
		
		private Key getSignKey() {
			byte[] keyByteCoder=Decoders.BASE64.decode(jwtSecret);
			return Keys.hmacShaKeyFor(keyByteCoder);
		}
}
