package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Bank_Acct_Transactions;
import domain.Transactions;
import util.ConnectionUtil;

public class TransactionsDaoImpl implements TransactionsDao {

	private String filename = "connection.properties";
	
	@Override
	public Transactions getTXById(int id) {
		Transactions t = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT * FROM TRANSACTIONS WHERE TRANSACTIONS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("TRANSACTIONS_ID");
				String time = rs.getString("TRANSACTIONS_TIME");
				String type = rs.getString("TRANSACTIONS_TYPE");
				int modifier = rs.getInt("TRANSACTIONS_MODIFIER");
				t = new Transactions(pId, time, type, modifier);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return t;
	}

	@Override
	public void printTransactions(int id) {
		PreparedStatement pstmt = null;
		List<Bank_Acct_Transactions> t = new ArrayList<>();
		List<Transactions> tr = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "SELECT * FROM BANK_ACCT_TRANSACTIONS WHERE BANK_ACCT_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			// Will give us transactions associated with the given bank account id
			while (rs.next()) {
				int bId = rs.getInt("BANK_ACCT_ID");
				int tId = rs.getInt("TRANSACTIONS_ID");
				t.add(new Bank_Acct_Transactions(bId, tId));
			}

			// Obtain each Transaction according to each transaction ID that we have obtained
			for (int i = 0; i < t.size(); i++) {
				sql = "SELECT * FROM TRANSACTIONS WHERE TRANSACTIONS_ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, t.get(i).getTrId());
				rs = pstmt.executeQuery();

				// Will give us the appropriate transaction id
				while (rs.next()) {
					int tId = rs.getInt("TRANSACTIONS_ID");
					String time = rs.getString("TRANSACTIONS_TIME");
					String type = rs.getString("TRANSACTIONS_TYPE");
					int modifier = rs.getInt("TRANSACTIONS_MODIFIER");
					tr.add(new Transactions(tId, time, type ,modifier));
				}
			}
			
			// If there are no transactions alert the user
			if(tr.size() == 0) {
				System.out.println("There are no transactions for this account!");
			} else {
				// Print out each transaction
				for(int i = 0; i < tr.size(); i++)
					System.out.println(tr.get(i).toString());
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
