package com.example.demo.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.User;

@FeignClient(name = "Salary-Service")
public interface EmployeeSalaryServiceConsumer {

	@GetMapping("/salary/getSalary")
	public String getSalary();
	
	@GetMapping("/salary/msg/{code}")
	public String getMsg(
			@PathVariable("code")String code );
	@PostMapping("/salary/saveUser")
	public ResponseEntity<?>  saveUserData(@RequestBody User user);
	
}
