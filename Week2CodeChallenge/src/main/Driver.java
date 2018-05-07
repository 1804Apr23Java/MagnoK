package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.DepartmentDao;
import dao.DepartmentDaoImpl;

public class Driver {
	public static void main(String[] args) {
		/*
		 * // Test connection
		try {
			Connection con = ConnectionUtil.getConnectionFromFile("connection.properties");
			System.out.println(con.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		Scanner s = new Scanner(System.in);
		
		System.out.println("Processing Department Salaries Before raise...");
		DepartmentDao dd = new DepartmentDaoImpl();
		dd.printEachDeptWithAvgSal();
		
		System.out.println("Enter which department to give a raise to");
		
		int dept = 0;
		try {
			dept = s.nextInt();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		
		System.out.println("Giving Department "+ dept + " a raise!...");
		System.out.println("Processing Department Salaries After raise...");
		dd.giveRaiseToDept(dept);
		
	}
}
