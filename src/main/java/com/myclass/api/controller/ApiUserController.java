package com.myclass.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginUserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;


@RestController
@ResponseBody
@RequestMapping("/api/")
public class ApiUserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	// @CrossOrigin(origins = "http://localhost:8080/api")
	@CrossOrigin(origins="*")
	public ResponseEntity<List<User>> get() {
		List<User> users = userRepository.findAll();
		if (users == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/user/{email}")
	// @CrossOrigin(origins = "http://localhost:8080/api")
	@CrossOrigin(origins="*")
	public Object getUserByEmail(@PathVariable("email") String email) {
		User user = userRepository.findByEmail(email);
		System.out.println(user);
		if (user == null) {
			return new ResponseEntity<User>(user,HttpStatus.NO_CONTENT);
		}
		
		LoginUserDto loginUserDto = new LoginUserDto(user.getEmail(), user.getName(), user.getPassword());
		System.out.println(loginUserDto.toString());
		return new ResponseEntity<LoginUserDto>(loginUserDto, HttpStatus.OK);
	}
	
	@PostMapping("user/add")
	// @CrossOrigin(origins = "http://localhost:8080/api")
	@CrossOrigin(origins="*")
	public Object post(String emailRegister, String nameRegister, String passwordRegister) {
		
		System.out.println(emailRegister);
		System.out.println(nameRegister);
		System.out.println(passwordRegister);
		//List<User> users = userRepository.findAll();
		User user = new User(emailRegister, nameRegister, passwordRegister);
		
		userRepository.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
