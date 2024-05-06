package com.fitnessarea.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	@PostMapping("/admindashboard")
	public ResponseEntity<String> adminDashBoard(){
		return ResponseEntity.ok("AdminDashboard");
	}

}
