package util;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

public class RequestHelper {
	
	static EmployeeDao e = new EmployeeDaoImpl();

	public static String userLogin(String username, String password) {
		if(authenticateUser(username, password)) {
			if(isManager(username)) {
				return "Manager";
			} else
				return "Employee";
		} else
			return "InvalidLogin";
	}
	
	public static boolean authenticateUser(String username, String password) {
		if(e.checkPassword(username, password))
			return true;
		else
			return false;
	}
	
	public static boolean isManager(String username) {
		if(e.getManagerStatus(username))
			return true;
		else
			return false;
	}
}
