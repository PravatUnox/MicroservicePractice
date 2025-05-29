package com.example.demo.consumer;

import java.net.URI;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AddressRestConsumer {
	
	private final LoadBalancerClient loadBalancerClient;
	
	private final RestTemplate restTemplate;
	
	
	
	public AddressRestConsumer(LoadBalancerClient loadBalancerClient,RestTemplate restTemplate) {
		this.loadBalancerClient=loadBalancerClient;
		this.restTemplate=restTemplate;
	}
	
	public String getAddrData() {
		//get one ServiceInstance object using SID
		ServiceInstance serviceInstance=loadBalancerClient.choose("Address-Service");
		
		//read URI from SI
		URI uri =serviceInstance.getUri();
		
		//add path to SI and create URL
		String url = uri + "/address/data";
		
		ResponseEntity<String> response=restTemplate.getForEntity(url, String.class);
		
		return response.getBody();
	}

}
