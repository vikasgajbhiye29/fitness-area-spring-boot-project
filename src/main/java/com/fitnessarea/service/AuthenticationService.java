package com.fitnessarea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitnessarea.configuration.JwtService;
import com.fitnessarea.entity.User;
import com.fitnessarea.exceptionhandling.ResourceNotFoundExcption;
import com.fitnessarea.payload.AuthenticationResponse;
import com.fitnessarea.repository.UserRepository;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passworEncoder;
	@Autowired
	private JwtService JwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(User request) {
		User user = new User();
		user.setFname(request.getFname());
		user.setLname(request.getLname());
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setPassword(passworEncoder.encode(request.getPassword()));
		user.setRole(request.getRole().USER);
		user = this.userRepository.save(user);

		String token = JwtService.generateToken(user);
		return new AuthenticationResponse(token);
	}

	public AuthenticationResponse authenticate(User request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		User user = userRepository.findByUsername(request.getUsername());
		String token = JwtService.generateToken(user);
		return new AuthenticationResponse(token);

	}

}
