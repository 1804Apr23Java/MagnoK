package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.AccountUsers_Bank_Acct;
import util.ConnectionUtil;

public class AccountUsers_Bank_AcctDaoImpl implements AccountUsers_Bank_AcctDao {

	private String filename = "connection.properties";

	@Override
	public boolean checkAccount(int id) {
		PreparedStatement pstmt = null;
		List<AccountUsers_Bank_Acct> a = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "SELECT * FROM ACCOUNTUSERS_BANK_ACCT WHERE ACCOUNTUSERS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			// Will only give us one record usernames are unique
			if (rs.next()) {
				int aId = rs.getInt("ACCOUNTUSERS_ID");
				int bId = rs.getInt("BANK_ACCT_ID");
				a.add(new AccountUsers_Bank_Acct(aId, bId));
			}

			if (a.size() != 0)
				return true;

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void printBankAccounts(int id) {
		PreparedStatement pstmt = null;
		List<AccountUsers_Bank_Acct> a = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "SELECT * FROM ACCOUNTUSERS_BANK_ACCT WHERE ACCOUNTUSERS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			// Will only give us one record usernames are unique
			if (rs.next()) {
				int aId = rs.getInt("ACCOUNTUSERS_ID");
				int bId = rs.getInt("BANK_ACCT_ID");
				a.add(new AccountUsers_Bank_Acct(aId, bId));
			}

			for (int i = 0; i < a.size(); i++)
				System.out.println(a.get(i));

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
