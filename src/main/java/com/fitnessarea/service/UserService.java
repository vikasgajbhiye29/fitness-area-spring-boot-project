package com.fitnessarea.service;

import java.util.List;

import com.fitnessarea.entity.User;

public interface UserService {

	User updateUser(User user, int uid);

	void deleteUser(int uid);

	User getUserById(int uid);

	List<User> listOfUser();

}
