package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import domain.Employee;
import util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private String filename = "connection.properties";

	public boolean checkPassword(String user, String password) {
		PreparedStatement pstmt = null;
		String name = "";
		String pass = "";

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
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

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

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
}
