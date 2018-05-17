package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {		
		/*
		 /* Test Connection	*/
		try {
			Connection con = ConnectionUtil.getConnectionFromFile();
			System.out.println(con.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
//		Login l = new Login();
//		EmployeeDao e = new EmployeeDaoImpl();
//		Employee k = new Employee();
//		
//		k = e.getEmployeeByUsername("kmagno");
//		System.out.println(k);
//		
		
		//User Logging in
//		l.loginStart();
//		System.out.println("Login Successful!");
		
		//Checks if Manager or not
		
		
	}

}
