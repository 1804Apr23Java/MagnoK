package dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Employee;
import domain.Employee_Reimbursements;
import domain.Employee_Reimbursements_Reimb;
import domain.Reimbursements;
import util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	public boolean checkPassword(String user, String password) {
		PreparedStatement pstmt = null;
		String name = "";
		String pass = "";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			// Declare prepared statement
			String sql = "SELECT EMPLOYEE_USERNAME, EMPLOYEE_PASSWORD FROM EMPLOYEE WHERE EMPLOYEE_USERNAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();

			// Will only give us one record usernames are unique
			if (rs.next()) {
				name = rs.getString("EMPLOYEE_USERNAME");
				pass = rs.getString("EMPLOYEE_PASSWORD");
			}

			con.close();

			// Compare input password with DB password
			if (user.equals(name) && pass.equals(password))
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Employee getEmployeeByUsername(String user) {
		Employee emp = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// use a prepared statement
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_USERNAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();

			// do something with result
			if (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address = rs.getString("EMPLOYEE_ADDRESS");
				String city = rs.getString("EMPLOYEE_CITY");
				String state = rs.getString("EMPLOYEE_STATE");
				String zip = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				emp = new Employee(pId, lastName, firstName, username, password, email, title, birthday, address, city,
						state, zip, phone, isManager);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return emp;
	}

	@Override
	public boolean getManagerStatus(String user) {
		PreparedStatement pstmt = null;
		boolean isManager = false;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			// Declare prepared statement
			String sql = "SELECT EMPLOYEE_MANAGERSTATUS FROM EMPLOYEE WHERE EMPLOYEE_USERNAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();

			// Will only give us one record usernames are unique
			if (rs.next()) {
				isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
			}

			con.close();

			// Will only return true if the user is a manager
			if(isManager)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Employee updateEmployeeEmail(int id, String email) {
		Employee e = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Employee
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE_EMAIL = ? WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			// Get Employee
			sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email2 = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address = rs.getString("EMPLOYEE_ADDRESS");
				String city = rs.getString("EMPLOYEE_CITY");
				String state = rs.getString("EMPLOYEE_STATE");
				String zip = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				e = new Employee(pId, lastName, firstName, username, password, email2, title, birthday, address, city,
						state, zip, phone, isManager);
			}

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public Employee updateEmployeeBirthday(int id, java.sql.Date birthday) {
		Employee e = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Employee
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE_BIRTHDAY = ? WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, birthday);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			// Get Employee
			sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email2 = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday2 = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address = rs.getString("EMPLOYEE_ADDRESS");
				String city = rs.getString("EMPLOYEE_CITY");
				String state = rs.getString("EMPLOYEE_STATE");
				String zip = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				e = new Employee(pId, lastName, firstName, username, password, email2, title, birthday2, address, city,
						state, zip, phone, isManager);
			}

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public Employee updateEmployeeAddress(int id, String address) {
		Employee e = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Employee
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE_ADDRESS = ? WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			// Get Employee
			sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email2 = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday2 = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address2 = rs.getString("EMPLOYEE_ADDRESS");
				String city = rs.getString("EMPLOYEE_CITY");
				String state = rs.getString("EMPLOYEE_STATE");
				String zip = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				e = new Employee(pId, lastName, firstName, username, password, email2, title, birthday2, address2, city,
						state, zip, phone, isManager);
			}

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public Employee updateEmployeeCity(int id, String city) {
		Employee e = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Employee
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE_CITY = ? WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, city);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			// Get Employee
			sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email2 = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday2 = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address2 = rs.getString("EMPLOYEE_ADDRESS");
				String city2 = rs.getString("EMPLOYEE_CITY");
				String state = rs.getString("EMPLOYEE_STATE");
				String zip = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				e = new Employee(pId, lastName, firstName, username, password, email2, title, birthday2, address2, city2,
						state, zip, phone, isManager);
			}

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public Employee updateEmployeeState(int id, String state) {
		Employee e = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Employee
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE_STATE = ? WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			// Get Employee
			sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email2 = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday2 = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address2 = rs.getString("EMPLOYEE_ADDRESS");
				String city2 = rs.getString("EMPLOYEE_CITY");
				String state2 = rs.getString("EMPLOYEE_STATE");
				String zip = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				e = new Employee(pId, lastName, firstName, username, password, email2, title, birthday2, address2, city2,
						state2, zip, phone, isManager);
			}

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public Employee updateEmployeeZip(int id, String zip) {
		Employee e = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Employee
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE_ZIP = ? WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, zip);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			// Get Employee
			sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email2 = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday2 = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address2 = rs.getString("EMPLOYEE_ADDRESS");
				String city2 = rs.getString("EMPLOYEE_CITY");
				String state2 = rs.getString("EMPLOYEE_STATE");
				String zip2 = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				e = new Employee(pId, lastName, firstName, username, password, email2, title, birthday2, address2, city2,
						state2, zip2, phone, isManager);
			}

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public Employee updateEmployeePhoneNumber(int id, String phonenumber) {
		Employee e = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Employee
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE_PHONE = ? WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phonenumber);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			// Get Employee
			sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email2 = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday2 = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address2 = rs.getString("EMPLOYEE_ADDRESS");
				String city2 = rs.getString("EMPLOYEE_CITY");
				String state2 = rs.getString("EMPLOYEE_STATE");
				String zip2 = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				e = new Employee(pId, lastName, firstName, username, password, email2, title, birthday2, address2, city2,
						state2, zip2, phone, isManager);
			}

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> e = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Search for matching reimbursements with employee id
			String sql = "SELECT * FROM EMPLOYEE ORDER BY EMPLOYEE_ID ASC";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int pId = rs.getInt("EMPLOYEE_ID");
				String lastName = rs.getString("EMPLOYEE_LASTNAME");
				String firstName = rs.getString("EMPLOYEE_FIRSTNAME");
				String username = rs.getString("EMPLOYEE_USERNAME");
				String password = rs.getString("EMPLOYEE_PASSWORD");
				String email2 = rs.getString("EMPLOYEE_EMAIL");
				String title = rs.getString("EMPLOYEE_TITLE");
				Date birthday2 = rs.getDate("EMPLOYEE_BIRTHDAY");
				String address2 = rs.getString("EMPLOYEE_ADDRESS");
				String city2 = rs.getString("EMPLOYEE_CITY");
				String state2 = rs.getString("EMPLOYEE_STATE");
				String zip2 = rs.getString("EMPLOYEE_ZIP");
				String phone = rs.getString("EMPLOYEE_PHONE");
				boolean isManager = rs.getBoolean("EMPLOYEE_MANAGERSTATUS");
				e.add(new Employee(pId, lastName, firstName, username, password, email2, title, birthday2, address2, city2,
						state2, zip2, phone, isManager));
			}

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return e;
	}
}
