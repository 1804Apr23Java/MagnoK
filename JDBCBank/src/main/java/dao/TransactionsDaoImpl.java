package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Bank_Acct;
import domain.Transactions;
import util.ConnectionUtil;

public class TransactionsDaoImpl implements TransactionsDao {

	private String filename = "connection.properties";
	
	@Override
	public Transactions getTXById(int id) {
		Transactions t = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "SELECT * FROM TRANSACTIONS WHERE TRANSACTIONS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			// do something with result
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
}
