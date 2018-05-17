package dao;

import domain.Employee;

public interface EmployeeDao {
	public boolean checkPassword(String user, String password);
	public Employee getEmployeeByUsername(String user);
	public boolean getManagerStatus(String user);
}
