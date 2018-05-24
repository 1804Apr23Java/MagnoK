package dao;

import java.sql.Date;
import java.util.List;

import domain.Employee;

public interface EmployeeDao {
	public boolean checkPassword(String user, String password);
	public Employee getEmployeeByUsername(String user);
	public boolean getManagerStatus(String user);
	public Employee updateEmployeeEmail(int id, String email);
	public Employee updateEmployeeBirthday(int id, Date birthday);
	public Employee updateEmployeeAddress(int id, String address);
	public Employee updateEmployeeCity(int id, String city);
	public Employee updateEmployeeState(int id, String state);
	public Employee updateEmployeeZip(int id, String zip);
	public Employee updateEmployeePhoneNumber(int id, String phonenumber);
	public List<Employee> getAllEmployees();
}
