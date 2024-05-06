package com.fitnessarea.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitnessarea.entity.User;
import com.fitnessarea.exceptionhandling.ResourceNotFoundExcption;
import com.fitnessarea.repository.UserRepository;
import com.fitnessarea.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passworEncoder;


	@Override
	public User updateUser(User user, int uid) {
		User newuser = this.userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundExcption("UserId : "+uid+" is  Not Available For Update"));
		newuser.setFname(user.getFname());
		newuser.setLname(user.getLname());
		newuser.setEmail(user.getEmail());
		newuser.setUsername(user.getUsername());
		newuser.setPassword(passworEncoder.encode(user.getPassword()));
		newuser.setRole(user.getRole().USER);
		return this.userRepository.save(newuser);
	}

	@Override
	public void deleteUser(int uid) {
		User user = this.userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundExcption("UserID : "+uid+" is Not Present For Deletion"));
		this.userRepository.delete(user);

	}

	@Override
	public User getUserById(int uid) {
		User find = this.userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundExcption("Provided User Id "+uid+" Is Not Available"));
		return find;
	}

	@Override
	public List<User> listOfUser() {
		List<User> userList = this.userRepository.findAll();
		List<User> list = userList.stream().map(user -> user).collect(Collectors.toList());
		return list;
	}
	
	

}
