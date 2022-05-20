package com.example.demo;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollbar.notifier.Rollbar;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
public class HelloController {


	@Autowired
	private Rollbar rollbar;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/test")
	public String test() {

		rollbar.info("In /test");

		throw new RuntimeException("An error has occurred");
	}
}
