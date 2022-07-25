package com.example.demo;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rollbar.notifier.Rollbar;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}
