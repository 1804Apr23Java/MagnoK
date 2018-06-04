package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Users;
import com.revature.repository.UsersRepository;

@RestController
@RequestMapping(value="/users")
public class UsersController {

	@Autowired
	UsersRepository ur;

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<>(ur.findAll(), HttpStatus.OK);
	}

	// test path for HTML page
	@GetMapping(value = "/HelloPage")
	public String getStaticFlashcardPage() {
		return "Hello";
	}

}
