package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.AccountUsersDao;
import dao.AccountUsersDaoImpl;
import domain.AccountUsers;

public class User {

	Scanner s = new Scanner(System.in);
	// Super User and Database Info
	boolean superuser = false;
	String[] properties = new String[3];
	String databaseInfo = "Working!";

	Bank b = new Bank();
	AccountUsersDao ac = new AccountUsersDaoImpl();
	AccountUsers a;

	// Account Id for the user accessing currently
	String userAccountName;

	public void logIn() {

		// Get input from user
		inputUserName();
		
		// Check if user is superuser
		if (a.isSuperuser()) {
			superUserMenu();
		}
		
		// After super user is done will put username back in variable
		userAccountName = a.getUsername();

		b.initialChooseAccount(userAccountName);
	}

	public void superUserMenu() {

		// Will turn true once user wants to proceed with their bank account
		boolean cont = false;
		int choice = 0;

		System.out.println("SuperUser Detected!");
		System.out.println("Enter 0 - to continue with program\n" + "Enter 1 - to access list of usernames\n"
				+ "Enter 2 - to create a username\n" + "Enter 3 - to update a user's accessibility\n"
				+ "Enter 4 - to delete a username");

		try {
			choice = s.nextInt();
		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println("Invalid Input! Please make sure you are entering a number!");
			s.next();
			superUserMenu();
		}

		switch (choice) {
		case 0:
			System.out.println("Proceeding with program...");
			b.initialChooseAccount(userAccountName);
			break;
		case 1:
			System.out.println("Processing accounts...");
			ac.superUPrintOutUsers();
			superUserMenu();
			break;
		case 2:
			superUsercreateAccount();
			break;
		case 3:
			superUserUpdate();
			break;
		case 4:
			superUserDelete();
			break;
		default:
			System.out.println("Invalid Input! Please enter a number from 0-4!");
			superUserMenu();
		}
	}
	
	public void superUsercreateAccount() {
		String newU = "";
		while (true) {
			// Prompt the user to enter a new username and password
			System.out.println("Creating new account\n" + "Please input username:");
			String newUser = s.nextLine();
			System.out.println(newUser);
			
			newUser = newUser.toLowerCase();
			
			if(newUser.length() < 2) {
				System.out.println("Please enter valid username!");
				createAccount();
			}
			
			// Check if username exists
			System.out.println("Checking username... " + newUser);
			if (ac.userExists(newUser)) {
				System.out.println("Username already exists! Please enter a different one");
			} else {
				System.out.println("Username is available!\n" + "Please enter a password:");
				newU = newUser;
				break;
			}
		}
		
		superUsercreatePassword(newU);
		
	}
	
	public void superUsercreatePassword (String user) {
		// creating new password
		String newPassword = s.nextLine();
		ac.newUser(user, newPassword);
		System.out.println("New user account has been created");
		superUserMenu();
	}
	
	public void superUserUpdate() {
		while(true) {
			System.out.println("Here is a list of all existing accounts");
			ac.superUPrintOutUsers();
			System.out.println("Enter username of account you would like to grant super user privileges to\n"
					         + "Or type exit to return to menu");
			String suUpdate = s.nextLine();
			suUpdate.toLowerCase();
			
			//Checks username
			if(suUpdate.equals("exit")) {
				break;
			} else if(checkUser(suUpdate)) {
				System.out.println("Granting privileges to " + suUpdate + "...");
				ac.superUGrantAccess(suUpdate);
				System.out.println("Complete!");
				superUserMenu();
			}
		}
		superUserMenu();
	}
	
	public void superUserDelete() {
		while(true) {
			System.out.println("Here is a list of all existing accounts");
			ac.superUPrintOutUsers();
			System.out.println("Enter username of account you would like to delete\n"
					         + "Or type exit to return to menu");
			String suDelete = s.nextLine();
			suDelete.toLowerCase();
			
			//Checks username
			if(suDelete.equals("exit")) {
				break;
			} else if(checkUser(suDelete)) {
				System.out.println("Deleting username: " + suDelete + "...");
				ac.superUDelete(suDelete);
				System.out.println("Complete!");
				superUserMenu();
			}
		}
		superUserMenu();
	}

	public void createAccount() {
		while (true) {
			
			// Prompt the user to enter a new username and password
			System.out.println("Creating new account\n" + "Please input username:");
			String newUser = s.nextLine();
			System.out.println(newUser);
			
			newUser = newUser.toLowerCase();
			
			if(newUser.length() < 2) {
				System.out.println("Please enter valid username!");
				createAccount();
			}
			
			// Check if username exists
			System.out.println("Checking username... " + newUser);
			if (ac.userExists(newUser)) {
				System.out.println("Username already exists! Please enter a different one");
			} else {
				System.out.println("Username is available!\n" + "Please enter a password:");
				userAccountName = newUser;
				break;
			}
		}
		// creating new password
		String newPassword = s.nextLine();
		ac.newUser(userAccountName, newPassword);
		System.out.println("New user account has been created");
	}

	public void inputUserName() {
		String user = "";
		while (true) {
			System.out.println(
					"Please input username: \n" + "Or enter 'create' to make a new username\n" + "Not case-sensitive");
			user = s.nextLine();

			user = user.toLowerCase();
			System.out.println("Checking username... " + user);
			if (checkUser(user)) {
				break;
			}
		}
		if (user.equals("create")) {
			createAccount();
			inputUserName();
		}
			
		userAccountName = user;
		passwordInput(user);
	}

	public boolean checkUser(String user) {
		if (user.length() < 1) {
			System.out.println("Please enter valid username");
			return false;
		} else if (user.equals("create")) {
			return true;
		} else {
			if (ac.userExists(user)) {
				System.out.println("Username exists!");
				return true;
			} else {
				System.out.println("Username doesn't exist! Please try again!");
				return false;
			}
		}
	}

	public void passwordInput(String user) {
		String password = "";
		int failCount = 0;

		while (true) {
			if (failCount == 3) {
				System.out.println("3 failed attempts! Try again later");
				inputUserName();
			}
			System.out.println("Please input password: ");
			password = s.nextLine();
			System.out.println("Checking password... ");
			if (checkPassword(user, password)) {
				System.out.println("Login Success!");
				a = ac.getAUByUsername(userAccountName);
				break;
			} else {
				System.out.println("Password has failed! Please try again!");
				failCount++;
			}

		}
	}

	public boolean checkPassword(String user, String password) {
		if (ac.checkPassword(user, password)) {
			return true;
		} else
			return false;
	}
}
