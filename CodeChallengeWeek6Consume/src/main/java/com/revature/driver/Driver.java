package com.revature.driver;

import org.springframework.web.client.RestTemplate;

import com.revature.domain.Users;

public class Driver {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
        Users users = restTemplate.getForObject("http://localhost:8088/users/all", Users.class);
        System.out.println(users);
	}

}
