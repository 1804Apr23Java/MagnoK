package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Users;
import com.revature.repository.UsersRepository;

@Service(value="usersService")
public class UsersService {
	
	@Autowired
	UsersRepository ur;
	
	public List<Users> getUsers() {
		List<Users> users = ur.getUsers();
		return users;
	}

}
