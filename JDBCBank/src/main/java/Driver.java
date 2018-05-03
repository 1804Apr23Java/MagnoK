import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		UserInteractions u = new UserInteractions();
		
		// Super User and Database Info
		boolean superuser = false;
		String [] properties = new String [3];
		String databaseInfo = "Working!";

		// Get input from user
		System.out.println("Please input username: ");
		String user = s.nextLine();
		
		System.out.println("Please input password: ");
		String pass = s.nextLine();
		
		boolean ifAccountExists = true;
		// See if username exists in database
		if(u.logIn(user, pass)) {
			//search database to see if user has any account, if not prompt to create an account 
			if(ifAccountExists) {
				u.accountDisplay();
			}
		}
		// If not prompt user to create account
		// If user exists compare password on database
		// If wrong then prompt user to try again
		
		// If right then they have access to their account
		/*
		 * A user can view their own existing accounts and balances. 
			A user can create an account.
			A user can delete an account if it is empty.  
			A user can add to or withdraw from an account. 
			A user can execute multiple deposits or withdrawals in a session. 
			A user can logout.
		 */
		
		// Store superuser username and password in a properties.txt file
		if(superuser) {
			
			properties[0] = user;
			properties[1] = pass;
			properties[2] = databaseInfo;
			String filename = "C:\\GitRepos\\MagnoK\\JDBCBank\\src\\main\\properties\\properties.txt";
	
			try {
				u.addPropertiesInfo(filename, properties);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
