package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Value("${server.port}")
	private String port;

	@GetMapping("/data")
	public String getData() {
		return "FROM ADDRESS:"+port;
	}

}
