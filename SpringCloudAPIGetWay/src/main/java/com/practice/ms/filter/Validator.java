package com.practice.ms.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import org.springframework.http.server.reactive.ServerHttpRequest;

@Component
public class Validator {

	public static final List<String> openEndPoint=List.of(
			"/registrationUser","/generateToken","/validateToken","/refreshtoken"
			);
	Predicate<ServerHttpRequest> isSecure=serverHttpRequest-> openEndPoint.stream()
			.noneMatch(uri-> serverHttpRequest.getURI().getPath().contains(uri));
}
