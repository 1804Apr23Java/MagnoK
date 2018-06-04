package com.revature.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {
		
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int userId;
	private GameStates gameStates;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String username;
	private boolean isHost;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public GameStates getGameStates() {
		return gameStates;
	}
	public void setGameStates(GameStates gameStates) {
		this.gameStates = gameStates;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isHost() {
		return isHost;
	}
	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", gameStates=" + gameStates + ", email=" + email + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", isHost="
				+ isHost + "]";
	}
}
