package com.example.demo.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.ConfigClientConfiguration;
import com.example.demo.model.Department;

@RestController
@RibbonClient(name = "CONFIGCLIENT", configuration = ConfigClientConfiguration.class)
@RequestMapping("ribbondept")
public class RibbonClientController {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() 
	{
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@GetMapping
	private Department getDepartment(@RequestParam(value = "deptNo") int deptNo) 
	{
		final String uri = "http://configclient/department?deptNo=" + deptNo;
		Department result = restTemplate.getForObject(uri, Department.class);
		return result;
	}

}
