package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Employee;
import domain.Reimbursements;
import util.ConnectionUtil;

public class ReimbursementsDaoImpl implements ReimbursementsDao{

	@Override
	public Reimbursements createReimbursement(Employee e, Reimbursements r) {
		PreparedStatement pstmt = null;
		// Will be reimbursement ID of new reimbursement
		int rId = 0;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			// Make a new transaction record and associate with bank account
			String sql = "INSERT INTO REIMBURSEMENTS (REIMBURSEMENTS_REQUESTNOTES, REIMBURSEMENTS_AMOUNT, REIMBURSEMENTS_REQUESTDATE, REIMBURSEMENTS_IMG) "
			    + "VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, r.getReqNotes());
			pstmt.setFloat(2, r.getAmount().floatValue());
			pstmt.setDate(3, r.getReqDate());
			pstmt.setString(4, r.getImg());
			pstmt.executeQuery();
			
			// Retrieves Primary key of newly made reimbursement
			sql = "SELECT MAX(REIMBURSEMENTS_ID) FROM REIMBURSEMENTS";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rId = rs.getInt("MAX(REIMBURSEMENTS_ID)");
			}
			// Updates Reimbursement with the correct reimbursement id
			r.setId(rId);
			
			// Inserts primary keys from employee and reimbursement to join table to make sure employee
			// is linked with reimbursement
			sql = "INSERT INTO EMPLOYEE_REIMBURSEMENTS (EMPLOYEE_ID, REIMBURSEMENTS_ID) VALUES (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, e.getId());
			pstmt.setInt(2, rId);
			pstmt.executeQuery();

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return r;
		
	}

}
