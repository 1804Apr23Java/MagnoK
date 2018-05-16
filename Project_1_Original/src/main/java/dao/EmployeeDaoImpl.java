package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
