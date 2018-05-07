package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.AccountUsersDao;
import dao.AccountUsersDaoImpl;
import dao.AccountUsers_Bank_AcctDao;
import dao.AccountUsers_Bank_AcctDaoImpl;
import dao.Bank_AcctDao;
import dao.Bank_AcctDaoImpl;
import dao.TransactionsDao;
import dao.TransactionsDaoImpl;
import domain.AccountUsers;
import domain.Bank_Acct;

public class Bank {

	Scanner s = new Scanner(System.in);
	AccountUsers_Bank_AcctDao aba = new AccountUsers_Bank_AcctDaoImpl();
	AccountUsersDao ac = new AccountUsersDaoImpl();
	Bank_AcctDao b = new Bank_AcctDaoImpl();
	TransactionsDao t = new TransactionsDaoImpl();
	Bank_Acct ba;
	String userName;
	int userId;

	public void initialChooseAccount(String user) {
		// Allow user to choose bank account with number input
		userName = user;
		System.out.println("Checking if a bank account exists...");
		// Print out user's bank accounts obtained from the database
		AccountUsers a = new AccountUsers();
		a = ac.getAUByUsername(user);
		userId = a.getId();
		if (checkIfAccountExists(userId)) {
			accountDisplay();
		} else {
			System.out.println("Account doesn't exist for username!");
			createBankAccount();
			accountDisplay();
		}
	}
	
	public boolean checkIfAccountExists(int id) {
		if(aba.checkAccount(id)) {
			return true;
		} else
			return false;
	}

	public void createBankAccount() {
		while (true) {
			System.out.println("Would you like to make an account?\n" + "Enter yes to make an account or\n"
					+ "Enter exit to close program.");
			String sc = s.nextLine();
			// Make user input non-case sensitive
			sc.toLowerCase();
			if (sc.equals("yes")) {
				// Make bank account
				System.out.println("Please enter your starting balance!\n" + "Only allowed up to $999999\n"
						+ "Only whole dollar amount no change allowed!");
				int funds = 0;

				try {
					funds = s.nextInt();
				} catch (InputMismatchException e) {
					// e.printStackTrace();
					System.out.println("Invalid Input! Please make sure you are entering a number between 0 - 999999!");
					s.next();
					createBankAccount();
				}

				if (funds < 999999) {
					System.out.println("Creating Bank Account...");
					b.createBankAcct(funds, userId);
					System.out.println("Bank Account has been created!");
					accountDisplay();
					// ERROR CHECK FOR WRONG INPUT (negative funds, wrong characters)
				} else if (funds > 999999) {
					System.out.println("Invalid Input! Make sure you are entering a correct value!");
				} else if (funds < 0)
					System.out.println("Invalid Input! Make sure you are entering a correct value!");

			} else if (sc.equals("exit")) {
				System.out.println("Have a nice day!");
				System.exit(0);
			} else {
				System.out.println("Invalid Entry! Please try again");
			}
		}
	}

	public void chooseAccount() {
		while (true) {
			System.out.println("Loading Bank Accounts...");
			// Print out bank accounts associated with username
			aba.printBankAccounts(userId);
			System.out.println("Please choose the Bank Account Number you wish to access");
			int choice = 0;

			// Checks if input is valid
			try {
				choice = s.nextInt();
			} catch (InputMismatchException e) {
				// e.printStackTrace();
				System.out.println("Invalid Input! Please make sure you are entering a valid number!");
				s.next();
				chooseAccount();
			}

			System.out.println("Checking Bank Account Number " + choice + "...");
			if (checkAccountNumber(choice, userId)) {
				System.out.println("Bank Account Confirmed!");
				// Pass in bank account info
				System.out.println("Loading Bank Account Options...");
				bankAccountOptions(ba = b.getByBAId(choice));
			} else
				System.out.println("Access denied! Make sure you are entering the correct account number!");

			// ERROR CHECK FOR WRONG INPUT

		}
	}
	
	public boolean checkAccountNumber(int b_num, int aId) {
		if(b.checkAccountNum(b_num, aId)) {
			return true;
		} else
			return false;
	}

	public void bankAccountOptions(Bank_Acct b) {
		System.out.println("Here are your options!");
		System.out.println("Enter 1-to view your balance\n" + "Enter 2-to make a withdrawal\n"
				+ "Enter 3-to make a deposit\n" + "Enter 4-to view your transactions\n"
				+ "Enter 5-to delete your account\n" + "Enter 6-to end your session");
		int choice = 0;

		// Checks if input is valid
		try {
			choice = s.nextInt();
		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println("Invalid Input! Please make sure you are entering a valid number!");
			s.next();
			bankAccountOptions(b);
		}

		// Pass in bank account info
		switch (choice) {
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
		// Passing in bank account ID
		System.out.println("Processing your transactions! Please wait!...");
		t.printTransactions(ba.getId());
		//System.out.println("Calculating Net Income! Please wait!...");
		//System.out.println("Net Income: " + t.calcNetIncome(ba.getId()));
	}

	public void deleteAccount() {
		System.out.println("Are you sure you want to delete your account?\n" + "Enter yes to confirm or\n"
				+ "Enter no to return to options menu");
		String sc = s.nextLine();
		sc.toLowerCase();
		switch (sc) {
		case "yes":
			// Delete account in SQL
			System.out.println("Deleting your bank account! Please wait...");
			b.deleteBankAcct(ba.getId());
			accountDisplay();
			break;
		case "no":
			// Return to options menu
			bankAccountOptions(ba);
			break;
		default:
			System.out.println("Invalid Input! Please try again...");
			deleteAccount();
		}
	}

	public void deposit() {
		viewBalance();
		System.out.println("Please enter the amount you wish to deposit:");
		int amount = 0;

		// Checks if input is valid
		try {
			amount = s.nextInt();
		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println("Invalid Input! Please make sure you are entering a valid number!");
			s.next();
			deposit();
		}

		// Check if amount is valid

		if (amount > 0) {
			// Update Bank Account for deposited funds
			System.out.println("Amount accepted. Processing your deposit...");
			b.deposit(ba.getId(), ba.getBalance() + amount, amount);
			System.out.println("Transaction Completed! Updating your account...");
			// Update bank account
			ba = b.getByBAId(ba.getId());
			// Show user updated balance
			viewBalance();
		} else {
			System.out.println("Invalid entry! Please try again.");
			deposit();
		}
	}

	public void withdrawal() {
		viewBalance();
		System.out.println("Please enter the amount you wish to withdraw:");
		int amount = 0;

		// Checks if input is valid
		try {
			amount = s.nextInt();
		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println("Invalid Input! Please make sure you are entering a valid number!");
			s.next();
			withdrawal();
		}

		if (amount <= ba.getBalance()) {
			// Update Bank Account for withdrawn funds
			System.out.println("Amount accepted. Processing your withdrawl...");
			b.withdraw(ba.getId(), ba.getBalance() - amount, amount);
			System.out.println("Transaction Completed! Updating your account!");
			// Update bank account
			ba = b.getByBAId(ba.getId());
			// Show user updated balance
			viewBalance();
		} else {
			System.out.println("Insufficient Funds! Please try again...");
			withdrawal();
		}
	}

	public void viewBalance() {
		int amount = ba.getBalance();
		System.out.println("Balance : $" + amount);
	}

	public void accountDisplay() {
		System.out.println(
				"Enter 1-for choosing existing account\n" + "Enter 2-for creating an account\n" + "Enter 3-to exit");
		int choice = 0;

		try {
			choice = s.nextInt();
		} catch (InputMismatchException e) {
			// e.printStackTrace();
			System.out.println("Invalid Input! Please make sure you are entering the numbers 1-3 ONLY");
			s.next();
			accountDisplay();
		}

		switch (choice) {
		case 1:
			chooseAccount();
			break;
		case 2:
			createBankAccount();
			break;
		case 3:
			System.out.println("Thanks, have a nice day!");
			System.exit(0);
		default:
			accountDisplay();
			System.out.println("Invalid choice! Please try again!");
		}
	}
}
