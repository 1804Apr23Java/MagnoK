package main;

import java.util.Scanner;

import dao.AccountUsersDao;
import dao.AccountUsersDaoImpl;
import dao.AccountUsers_Bank_AcctDao;
import dao.AccountUsers_Bank_AcctDaoImpl;
import dao.Bank_AcctDao;
import dao.Bank_AcctDaoImpl;
import domain.AccountUsers;
import domain.Bank_Acct;

public class Bank {
	
	Scanner s = new Scanner(System.in);
	AccountUsers_Bank_AcctDao aba = new AccountUsers_Bank_AcctDaoImpl();
	AccountUsersDao ac = new AccountUsersDaoImpl();
	Bank_AcctDao b = new Bank_AcctDaoImpl();
	Bank_Acct ba;
	String userName;
	int userId;
	
	public void initialChooseAccount(String user) {
		//Allow user to choose bank account with number input
		userName = user;
		System.out.println("Checking if a bank account exists...");
		//Print out user's bank accounts obtained from the database
		AccountUsers a = new AccountUsers();
		a = ac.getAUByUsername(user);
		userId = a.getId();
		if(aba.checkAccount(userId)) {
			chooseAccount();
		} else {
			System.out.println("Account doesn't exists for username!");
			createBankAccount();
			chooseAccount();
		}
	}
	
	public void createBankAccount() {
		while(true) {
			System.out.println("Would you like to make an account?\n"
			         + "Enter yes to make an account or\n"
			         + "Enter exit to close program.");
			String sc = s.nextLine();
			// Make user input non-case sensitive
			sc.toLowerCase();
			if(sc.equals("yes")) {
				// Make bank account
				System.out.println("Please enter your starting balance!\n"
						         + "Only whole dollar amount no change allowed!");
				int funds = s.nextInt();
				System.out.println("Creating Bank Account...");
				b.createBankAcct(funds, userId);
				System.out.println("Bank Account has been created!");
				
				// ERROR CHECK FOR WRONG INPUT (negative funds, wrong characters)
				
			} else if (sc.equals("exit")) {
				System.out.println("Have a nice day!");
				System.exit(0);
			} else
				System.out.println("Invalid Entry! Please try again");
		}
	}
	
	public void chooseAccount() {
		while(true) {
			System.out.println("Loading Bank Accounts...");
			// Print out bank accounts associated with username
			aba.printBankAccounts(userId);
			System.out.println("Please choose the Bank Account Number you wish to access");
			int choice = s.nextInt();
			// Checks if input is valid
			if(choice == (int)choice) {
				System.out.println("Checking Bank Account Number " +choice+ "...");
				if(b.checkAccountNum(choice)) {
					System.out.println("Bank Account Confirmed!");
					// Pass in bank account info
					System.out.println("Loading Bank Account Options...");
					bankAccountOptions(ba = b.getByBAId(choice));
				}
			}
			
			// ERROR CHECK FOR WRONG INPUT
			
			
		}
	}

	public void bankAccountOptions(Bank_Acct b) {
		System.out.println("Here are your options!");
		System.out.println("Enter 1-to view your balance\n"
				+ "Enter 2-to make a withdrawal\n"
				+ "Enter 3-to make a deposit\n"
				+ "Enter 4-to view your transactions\n"
				+ "Enter 5-to delete your account\n"
				+ "Enter 6-to end your session");
		int choice = s.nextInt();
		// Pass in bank account info
		switch(choice) {
		case 1:
			viewBalance();
			bankAccountOptions(b);
			break;
		case 2:
			withdrawal();
			bankAccountOptions(b);
			break;
		case 3:
			deposit();
			bankAccountOptions(b);
			break;
		case 4:
			viewTransaction();
			bankAccountOptions(b);
			break;
		case 5:
			deleteAccount();
			bankAccountOptions(b);
			break;
		case 6:
			accountDisplay();
			break;
		default:
			System.out.println("Invalid Entry, Please  try again!");
			bankAccountOptions(b);
		}
	}

	public void viewTransaction() {
		//Prints out bank's transactions 
		
	}

	public void deleteAccount() {
		System.out.println("Are you sure you want to delete your account?");
		//Delete account in SQL
		accountDisplay();
	}

	public void deposit() {
		viewBalance();
		System.out.println("Please enter the amount you wish to deposit:");
		int amount = s.nextInt();
		// Add amount to bank account
	}

	public void withdrawal() {
		while(true) {
			viewBalance();
			System.out.println("Please enter the amount you wish to withdraw:");
			int amount = s.nextInt();
			int modifier = amount;
			
			
			// Check if amount is valid
			// ERROR CHECK
			
			
			if(amount <= ba.getBalance()) {			
				// Update Bank Account for withdrawn funds
				System.out.println("Amount accepted. Processing your withdrawl...");
				b.withdraw(ba.getId(), ba.getBalance() - amount, amount);
				System.out.println("Transaction Completed!");
				break;
			} else
				System.out.println("Insufficient Funds! Please try again...");
		}
	}

	public void viewBalance() {
		int amount = ba.getBalance();
		System.out.println("Balance : $" + amount);
	}

	public void createAccount() {
		// Add account to SQL
		System.out.println("Account Created!");
		accountDisplay();
	}

	public void accountDisplay() {
		System.out.println("Enter 1-for choosing existing account\n"
				+ "Enter 2-for creating an account\n"
				+ "Enter 3-to exit");
		int choice = s.nextInt();
		if(choice == 1) {
			chooseAccount();
		}
		if(choice==2) {
			createAccount();
		}
		if(choice==3) {
			System.out.println("Thanks, have a nice day!");
			System.exit(0);
		}
	}
}
