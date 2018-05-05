package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.AccountUsers;
import domain.AccountUsers_Bank_Acct;
import util.ConnectionUtil;

public class AccountUsersDaoImpl implements AccountUsersDao {

	private String filename = "connection.properties";

	public AccountUsers getAUByUsername(String user) {
		AccountUsers a = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "SELECT * FROM ACCOUNTUSERS WHERE ACCOUNTUSERS_USERNAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();

			// do something with result
			if (rs.next()) {
				int pId = rs.getInt("ACCOUNTUSERS_ID");
				String name = rs.getString("ACCOUNTUSERS_USERNAME");
				String password = rs.getString("ACCOUNTUSERS_PASSWORD");
				boolean superUser = rs.getBoolean("ACCOUNTUSERS_SUPERUSER");
				a = new AccountUsers(pId, name, password, superUser);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public void newUser(String user, String pass) {
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "INSERT INTO ACCOUNTUSERS (ACCOUNTUSERS_USERNAME, ACCOUNTUSERS_PASSWORD, ACCOUNTUSERS_SUPERUSER) VALUES (?,?,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			pstmt.executeQuery();

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean userExists(String user) {
		List<String> existingUsers = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "SELECT ACCOUNTUSERS_USERNAME FROM ACCOUNTUSERS";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String username = rs.getString("ACCOUNTUSERS_USERNAME");
				username = username.toLowerCase();
				existingUsers.add(username);
			}

			con.close();

			// Compare user name from parameter with usernames from DB
			for (int i = 0; i < existingUsers.size(); i++) {
				if (existingUsers.get(i).toString().equals(user))
					return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean checkPassword(String user, String password) {
		PreparedStatement pstmt = null;
		String name = "";
		String pass = "";

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "SELECT * FROM ACCOUNTUSERS WHERE ACCOUNTUSERS_USERNAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();

			// Will only give us one record usernames are unique
			if (rs.next()) {
				int pId = rs.getInt("ACCOUNTUSERS_ID");
				name = rs.getString("ACCOUNTUSERS_USERNAME");
				pass = rs.getString("ACCOUNTUSERS_PASSWORD");
				boolean superUser = rs.getBoolean("ACCOUNTUSERS_SUPERUSER");
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
