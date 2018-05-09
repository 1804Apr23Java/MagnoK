package main;

import java.util.Scanner;

import dao.AccountUsersDao;
import dao.AccountUsersDaoImpl;
import dao.AccountUsers_Bank_AcctDao;
import dao.AccountUsers_Bank_AcctDaoImpl;

public class Driver {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		User u = new User();
		
		u.logIn();

	}
}
