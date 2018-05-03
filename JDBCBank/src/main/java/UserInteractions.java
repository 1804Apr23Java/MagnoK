import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserInteractions {
	
	Scanner s = new Scanner(System.in);
	
	public boolean logIn(String user, String password) {
		String userName = "user";
		String passWord = "pass";
		
		//Sending to database to confirm username and password
		
		if(user.equals(userName)&&passWord.equals(password)) {
			return true;
		}
		
		return false;
	}
	
	public void addPropertiesInfo(String filename, String [] info) throws IOException {
		// Store superuser username/password and database info in a properties.txt file
		
		File properties = new File(filename);
		
		PrintWriter outputStream = new PrintWriter(filename);
		
		outputStream.println(info[0]);
		outputStream.println(info[1]);
		outputStream.println(info[2]);
		
		outputStream.close();
	}

	public void chooseAccount() {
		//Allow user to choose bank account with number input
		System.out.println("Please choose your account");
		//Print out user's bank accounts obtained from the database
		int choice = s.nextInt();
		// Pass in bank account info
		bankAccountOptions();
	}

	private void bankAccountOptions() {
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
			bankAccountOptions();
			break;
		case 2:
			withdrawal();
			bankAccountOptions();
			break;
		case 3:
			deposit();
			bankAccountOptions();
			break;
		case 4:
			viewTransaction();
			bankAccountOptions();
			break;
		case 5:
			deleteAccount();
			bankAccountOptions();
			break;
		case 6:
			accountDisplay();
			break;
		default:
			System.out.println("Invalid Entry, Please  try again!");
			bankAccountOptions();
		}
	}

	private void viewTransaction() {
		//Prints out bank's transactions 
		
	}

	private void deleteAccount() {
		System.out.println("Are you sure you want to delete your account?");
		//Delete account in SQL
		accountDisplay();
	}

	private void deposit() {
		viewBalance();
		System.out.println("Please enter the amount you wish to deposit:");
		int amount = s.nextInt();
		// Add amount to bank account
	}

	private void withdrawal() {
		viewBalance();
		System.out.println("Please enter the amount you wish to withdraw:");
		int amount = s.nextInt();
		// Take out amount from bank account
	}

	private void viewBalance() {
		int amount = 0;
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
		//u.choiceScreen(choice);
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
