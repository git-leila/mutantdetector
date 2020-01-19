package com.detector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatusController {

	@GetMapping("/ping")
	public @ResponseBody String status() {
		return "OK";
	}

}
