package it.dverrienti.demo.springbootsecurity.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String root() {
		return "/home";
	}
	
	@GetMapping("/home")
	public String home() {
		return "/home";
	}

}
