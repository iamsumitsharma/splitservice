package com.split.splitservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class HealthCheckController {
	
	@GetMapping("")
	public String ping() {
		return "Pinging from split service!!";
	}

}
