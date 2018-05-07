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

		// Sending to database to confirm username and password
		/*
		 * boolean ifAccountExists = true; // See if username exists in database
		 * if(u.logIn(user, pass)) { //search database to see if user has any account,
		 * if not prompt to create an account if(ifAccountExists) { u.accountDisplay();
		 * } }
		 */
		// Check if user is superuser
		if (a.isSuperuser()) {
			superUserMenu();
		}

		b.initialChooseAccount(userAccountName);

		/*
		 * // Store superuser username and password in a properties.txt file if
		 * (superuser) {
		 * 
		 * properties[0] = user; properties[1] = pass; properties[2] = databaseInfo;
		 * String filename =
		 * "C:\\GitRepos\\MagnoK\\JDBCBank\\src\\main\\properties\\properties.txt";
		 * 
		 * try { addPropertiesInfo(filename, properties); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } }
		 */
	}

	public void superUserMenu() {

		// Will turn true once user wants to proceed with their bank account
		boolean cont = false;
		int choice = 0;
		List<AccountUsers> a;

		System.out.println("SuperUser Detected!");
		System.out.println("Enter 0 - to continue with program\n" + "Enter 1 - to access list of usernames\n"
				+ "Enter 2 - to create a username\n" + "Enter 3 - to update a user's accessibility/username/password"
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
			a = ac.superUPrintOutUsers();
			superUserMenu();
			break;
		case 2:
			createAccount();
			superUserMenu();
			break;
		case 3:
		case 4:
		default:
		}
	}

	public void createAccount() {
		String newUser = "";
		while (true) {
			// Prompt the user to enter a new username and password
			System.out.println("Creating new account\n" + "Please input username:");
			newUser = s.nextLine();
			System.out.println(newUser);
			// Check if username exists
			newUser = newUser.toLowerCase();
			System.out.println("Checking username... " + newUser);
			if (ac.userExists(newUser)) {
				System.out.println("Username already exists! Please enter a different one");
			} else {
				System.out.println("Username is available!\n" + "Please enter a password:");
				break;
			}
		}
		// creating new password
		String newPassword = s.nextLine();
		ac.newUser(newUser, newPassword);
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
			if (user.length() < 1) {
				System.out.println("Please enter valid username");
			}
			if (user.equals("create")) {
				createAccount();
			} else {
				if (ac.userExists(user)) {
					System.out.println("Username exists!");
					break;
				} else {
					System.out.println("Username doesn't exist! Please try again!");
				}
			}
		}
		userAccountName = user;
		checkPassword(user);
	}

	public void checkPassword(String user) {
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
			if (ac.checkPassword(user, password)) {
				System.out.println("Login Success!");
				a = ac.getAUByUsername(userAccountName);
				break;
			} else {
				System.out.println("Password has failed! Please try again!");
				failCount++;
			}

		}

	}

	public void addPropertiesInfo(String filename, String[] info) throws IOException {
		// Store superuser username/password and database info in a properties.txt file

		File properties = new File(filename);

		PrintWriter outputStream = new PrintWriter(filename);

		outputStream.println(info[0]);
		outputStream.println(info[1]);
		outputStream.println(info[2]);

		outputStream.close();
	}
}
