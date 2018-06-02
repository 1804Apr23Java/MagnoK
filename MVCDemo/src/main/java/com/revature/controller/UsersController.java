package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Users;
import com.revature.service.UsersService;

@Controller("usersController")
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
	}

	// test path for HTML page
	@GetMapping(value = "/HelloPage")
	public String getStaticFlashcardPage() {
		return "Hello";
	}

}
