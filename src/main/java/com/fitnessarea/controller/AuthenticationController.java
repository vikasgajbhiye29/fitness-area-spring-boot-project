package com.fitnessarea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessarea.entity.User;
import com.fitnessarea.payload.AuthenticationResponse;
import com.fitnessarea.service.AuthenticationService;
@CrossOrigin( origins = "http://localhost:3000/", maxAge = 3600L)
@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) throws Exception{
		return ResponseEntity.ok(authService.register(user));
		
	}
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
		return ResponseEntity.ok(authService.authenticate(request));
		
		
	}
	
	
	
	

}
