package main;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import domain.Employee;

public class Driver {

	public static void main(String[] args) {		
		/*
		 /* Test Connection	
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
		EmployeeDao e = new EmployeeDaoImpl();
		Employee k = new Employee();
		
		k = e.getEmployeeByUsername("kmagno");
		System.out.println(k);
		
		
		//User Logging in
//		l.loginStart();
//		System.out.println("Login Successful!");
		
		//Checks if Manager or not
		
		
	}

}
