package com.example.demo;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollbar.notifier.Rollbar;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@RestController
public class HelloController {

	private static final Logger LOGGER = LoggerFactory.getLogger("com.example.demo");

	@Autowired
	private Rollbar rollbar;

	@RequestMapping("/")
	public String index() {
		LOGGER.error("LOGGER ERROR /");
		rollbar.info("On the home page");
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/test")
	public String test() {


		LOGGER.info("LOGGER INFO");
		LOGGER.warn("LOGGER WARN");
		LOGGER.error("LOGGER ERROR");
		rollbar.info("In /test");

		throw new RuntimeException("An error has occurred");
	}
}
