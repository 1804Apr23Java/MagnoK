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
import domain.Bank_Acct;
import util.ConnectionUtil;

public class Bank_AcctDaoImpl implements Bank_AcctDao {

	private String filename = "connection.properties";
	@Override
	public Bank_Acct getByBAId(int id) {

		Bank_Acct b = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "SELECT * FROM BANK_ACCT WHERE BANK_ACCT_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("BANK_ACCT_ID");
				int balance = rs.getInt("BANK_ACCT_BALANCE");
				b = new Bank_Acct(pId, balance);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return b;

	}
	
	
	@Override
	public void createBankAcct(int startingBal, int id) {
		PreparedStatement pstmt = null;
		int bId = 0;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			// Make new bank account with initial balance
			String sql = "INSERT INTO BANK_ACCT (BANK_ACCT_BALANCE) VALUES (?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startingBal);
			pstmt.executeQuery();
			
			// Retrieves Primary key of newly made bank account
			sql = "SELECT MAX(BANK_ACCT_ID) FROM BANK_ACCT";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bId = rs.getInt("MAX(BANK_ACCT_ID)");
			}
			
			// Inserts primary keys from account users and bank account to join table to make sure account
			// is linked with user
			sql = "INSERT INTO ACCOUNTUSERS_BANK_ACCT VALUES (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, bId);
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
	public boolean checkAccountNum(int id, int aId) {
		PreparedStatement pstmt = null;
		List<AccountUsers_Bank_Acct> aba = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "SELECT * FROM ACCOUNTUSERS_BANK_ACCT WHERE BANK_ACCT_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			// Will only give us results of matching bank account id
			while (rs.next()) {
				int auId = rs.getInt("ACCOUNTUSERS_ID");
				int bId = rs.getInt("BANK_ACCT_ID");
				aba.add(new AccountUsers_Bank_Acct(auId,bId));
			}

			// Check list for matching pair
			for(int i = 0; i < aba.size(); i++)
				if(aba.get(i).getAuId() == aId && aba.get(i).getBaId() == id)
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
	public void withdraw(int id, int amount, int modifier) {
		PreparedStatement pstmt = null;
		int tId = 0;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "UPDATE BANK_ACCT SET BANK_ACCT_BALANCE = ? WHERE BANK_ACCT_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			//Make modifier negative to display correctly on Transactions record
			modifier *= -1;
			
			// Make a new transaction record and associate with bank account
			sql = "INSERT INTO TRANSACTIONS (TRANSACTIONS_TIME, TRANSACTIONS_TYPE, TRANSACTIONS_MODIFIER) "
			    + "VALUES (TO_DATE(CURRENT_TIMESTAMP(8)+0),'withdraw',?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, modifier);
			pstmt.executeQuery();
			
			// Retrieves Primary key of newly made transaction
			sql = "SELECT MAX(TRANSACTIONS_ID) FROM TRANSACTIONS";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				tId = rs.getInt("MAX(TRANSACTIONS_ID)");
			}
			
			// Inserts primary keys from bank account and transactions to join table to make sure account
			// is linked with transaction
			sql = "INSERT INTO BANK_ACCT_TRANSACTIONS VALUES (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, tId);
			pstmt.executeQuery();

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deposit(int id, int amount, int modifier) {
		PreparedStatement pstmt = null;
		int tId = 0;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "UPDATE BANK_ACCT SET BANK_ACCT_BALANCE = ? WHERE BANK_ACCT_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, id);
			pstmt.executeQuery();
			
			// Make a new transaction record and associate with bank account
			sql = "INSERT INTO TRANSACTIONS (TRANSACTIONS_TIME, TRANSACTIONS_TYPE, TRANSACTIONS_MODIFIER) "
			    + "VALUES (TO_DATE(CURRENT_TIMESTAMP(8)+0),'deposit',?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, modifier);
			pstmt.executeQuery();
			
			// Retrieves Primary key of newly made transaction
			sql = "SELECT MAX(TRANSACTIONS_ID) FROM TRANSACTIONS";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				tId = rs.getInt("MAX(TRANSACTIONS_ID)");
			}
			
			// Inserts primary keys from bank account and transactions to join table to make sure account
			// is linked with transaction
			sql = "INSERT INTO BANK_ACCT_TRANSACTIONS VALUES (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, tId);
			pstmt.executeQuery();

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void deleteBankAcct(int id) {
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			// Make new bank account with initial balance
			String sql = "DELETE FROM BANK_ACCT WHERE BANK_ACCT_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
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
}
