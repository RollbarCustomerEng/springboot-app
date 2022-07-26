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
public class StoreController {

	private static final Logger LOGGER = LoggerFactory.getLogger("com.example.demo");

	@Autowired
	private Rollbar rollbar;

	@RequestMapping("/")
	public String index() {
		LOGGER.error("LOGGER ERROR /");
		rollbar.info("On the home page");
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/choose")
	public String choose() {

		this.chooseShirt("L", "blue");
		
		return "Your shirt has been chosen";
	}


	@RequestMapping("/buy")
	public void buy() throws Exception {

		Transaction txn = new Transaction("L", "red");
		txn.completeTransaction();
	}

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	private int chooseShirt(String size, String color) {

		try{
			this.checkShirtSizeAndColor(size, color);
		}
		catch(ShirtOptionsException ex){
			LOGGER.error("Invalid shirt options", ex);
		}
		
		return 1;
	}

	private void checkShirtSizeAndColor(String size, String color) throws ShirtOptionsException{

		if(size.equals("L") && color.equals("blue")){
			throw new ShirtOptionsException("Invalid color for shirt size");
		}
	}	
}
