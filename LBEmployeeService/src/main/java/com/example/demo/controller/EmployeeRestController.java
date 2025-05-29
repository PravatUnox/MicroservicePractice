package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.User;
import com.example.demo.consumer.AddressRestConsumer;
import com.example.demo.consumer.EmployeeSalaryServiceConsumer;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	private final AddressRestConsumer consumer;
	
	private final EmployeeSalaryServiceConsumer salaryConsumer;
	
	@Value("${my.app.title}")
	private String title;
	
	public EmployeeRestController( AddressRestConsumer consumer,EmployeeSalaryServiceConsumer salaryConsumer) {
		this.consumer=consumer;
		this.salaryConsumer=salaryConsumer;
		
	}

	@GetMapping("/info")
	public String showEmp() {
		return "FROM-EMP=>" + consumer.getAddrData() +" Config Server Data is "+ title;
	}
	
	
	@GetMapping("/data")
	public String getData() {
		return "FROM SALARY SERVICE =>" + salaryConsumer.getMsg("NIT-TEST-CODE");
	}
	
	@GetMapping("/salaryInfo")
	public String getSalary() {
		return "FROM SALARY SERVICE  Salary Info=>" + salaryConsumer.getSalary();
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<?>  saveUserData(@RequestBody User user){
		return salaryConsumer.saveUserData(user);
	}


}
