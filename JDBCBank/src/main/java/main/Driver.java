package main;

import java.util.Scanner;

import dao.AccountUsersDao;
import dao.AccountUsersDaoImpl;
import dao.AccountUsers_Bank_AcctDao;
import dao.AccountUsers_Bank_AcctDaoImpl;

public class Driver {

	public static void main(String[] args) {
		/*
		try {
			// Connection con = ConnectionUtil.getConnection();
			Connection con = ConnectionUtil.getConnectionFromFile("connection.properties");
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		Scanner s = new Scanner(System.in);
		User u = new User();
		
		u.logIn();
		
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		AccountUsersDao ac = new AccountUsersDaoImpl();
		System.out.println(ac.getAUById(23));
		
		Bank_AcctDao bc = new Bank_AcctDaoImpl();
		System.out.println(bc.getByBAId(2));
		
		TransactionsDao tc = new TransactionsDaoImpl();
		System.out.println(tc.getTXById(1));
		*/
	}
}
