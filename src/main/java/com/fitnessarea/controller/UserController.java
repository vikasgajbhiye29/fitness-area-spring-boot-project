package com.fitnessarea.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessarea.entity.User;
import com.fitnessarea.service.UserService;
@CrossOrigin( origins = "http://localhost:3000/", maxAge = 3600L)
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/userdashboard")
	public ResponseEntity<String> userDashboard(){
		return ResponseEntity.ok("User Dashboard");
	}
	@GetMapping("/userlist")
	public ResponseEntity<List<User>> userList(){
		List<User> userList = this.userService.listOfUser();
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	@DeleteMapping("/deleteuser/{uid}")
	public ResponseEntity<String> deleteUser(@PathVariable int uid) {
		this.userService.deleteUser(uid);
		return new ResponseEntity<String>("Delete User Successfully", HttpStatus.OK);
	}
	@PutMapping("/updateuser/{uid}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int uid){
		User user1 = this.userService.updateUser(user, uid);
		return new ResponseEntity<User>(user1,HttpStatus.OK);
	}
	
	
	
	
}
