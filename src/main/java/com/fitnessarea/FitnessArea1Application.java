package com.fitnessarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fitnessarea.entity.Role;
import com.fitnessarea.entity.User;
import com.fitnessarea.repository.UserRepository;

@SpringBootApplication
public class FitnessArea1Application implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FitnessArea1Application.class, args);
		System.out.println("Pragram starting....");
		
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setFname("vikas");
//		user.setLname("gajbhiye");
//		user.setUsername("vikasgajbhiye");
//		user.setEmail("gajbhiye.vikas29@gmail.com");
//		user.setPassword(passwordEncoder.encode("12345"));
//		user.setRole(Role.ADMIN);
//		User user1 = this.userRepository.save(user);
		

		
	}

}
