package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {		
		/*
		 * Test Connection		
		try {
			Connection con = ConnectionUtil.getConnectionFromFile("connection.properties");
			System.out.println(con.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		Login l = new Login();
		
		//User Logging in
		l.loginStart();
		
		
	}

}
