package main;

import java.util.Scanner;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

public class Login {

	Scanner s = new Scanner(System.in);
	EmployeeDao e = new EmployeeDaoImpl();

	public void loginStart() {
		System.out.println("Hello!\n" + "Please enter your username:");
		String user = s.nextLine();
		System.out.println("Please enter your password:");
		String pass = s.nextLine();
		
		if(checkPass(user,pass)) {
			// Proceeds to next step
			return;
		} else {
			System.out.println("Wrong Username or Password. Please Try again!");
			loginStart();
		}
	}

	public boolean checkPass(String user, String pass) {
		// Checks user name and password
		if (e.checkPassword(user, pass)) {
			return true;
		} else
			return false;
	}

}
