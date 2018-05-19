package dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import domain.Employee;
import domain.Employee_Reimbursements;
import domain.Reimbursements;
import util.ConnectionUtil;

public class ReimbursementsDaoImpl implements ReimbursementsDao {

	@Override
	public Reimbursements createReimbursement(Employee e, Reimbursements r) {
		PreparedStatement pstmt = null;
		// Will be reimbursement ID of new reimbursement
		int rId = 0;

		// Get current date
		Calendar c = Calendar.getInstance();
		Date d = new Date(c.getTime().getTime());

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			// Make a new transaction record and associate with bank account
			String sql = "INSERT INTO REIMBURSEMENTS (REIMBURSEMENTS_STATUS, REIMBURSEMENTS_REQUESTNOTES, REIMBURSEMENTS_AMOUNT, REIMBURSEMENTS_REQUESTDATE, REIMBURSEMENTS_IMG) "
					+ "VALUES ('PENDING',?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, r.getReqNotes());
			pstmt.setFloat(2, r.getAmount().floatValue());
			pstmt.setDate(3, d);
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

			// Inserts primary keys from employee and reimbursement to join table to make
			// sure employee
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

	@Override
	public Reimbursements getReimbursementByID(int id) {
		Reimbursements r = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			String sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int pId = rs.getInt("REIMBURSEMENTS_ID");
				String status = rs.getString("REIMBURSEMENTS_STATUS");
				String reqNotes = rs.getString("REIMBURSEMENTS_REQUESTNOTES");
				BigDecimal amount = new BigDecimal(String.valueOf(rs.getFloat("REIMBURSEMENTS_AMOUNT")));
				Date reqQate = rs.getDate("REIMBURSEMENTS_REQUESTDATE");
				String img = rs.getString("REIMBURSEMENTS_IMG");
				r = new Reimbursements(pId, status, reqNotes, amount, reqQate, img);
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return r;
	}

	@Override
	public List<Reimbursements> getPendingReimbursements(int id) {
		List<Reimbursements> r = new ArrayList<>();
		List<Employee_Reimbursements> er = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Search for matching reimbursements with employee id
			String sql = "SELECT * FROM EMPLOYEE_REIMBURSEMENTS WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int empId = rs.getInt("EMPLOYEE_ID");
				int reiId = rs.getInt("REIMBURSEMENTS_ID");
				int manId = rs.getInt("REIMBURSEMENTS_MANAGERID");
				er.add(new Employee_Reimbursements(empId, reiId, manId));
			}
			
			// Search list for pending requests
			for(int i = 0; i < er.size(); i++) {
				sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_ID = ? AND REIMBURSEMENTS_STATUS = 'PENDING'";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, er.get(i).getReiId());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int pId = rs.getInt("REIMBURSEMENTS_ID");
					String status = rs.getString("REIMBURSEMENTS_STATUS");
					String reqNotes = rs.getString("REIMBURSEMENTS_REQUESTNOTES");
					BigDecimal amount = new BigDecimal(String.valueOf(rs.getFloat("REIMBURSEMENTS_AMOUNT")));
					Date reqQate = rs.getDate("REIMBURSEMENTS_REQUESTDATE");
					String img = rs.getString("REIMBURSEMENTS_IMG");
					// Will only add to list if status is pending
					r.add(new Reimbursements(pId, status, reqNotes, amount, reqQate, img));
				}
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return r;
	}

	@Override
	public List<Reimbursements> getAllReimbursements(int id) {
		List<Reimbursements> r = new ArrayList<>();
		List<Employee_Reimbursements> er = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Search for matching reimbursements with employee id
			String sql = "SELECT * FROM EMPLOYEE_REIMBURSEMENTS WHERE EMPLOYEE_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int empId = rs.getInt("EMPLOYEE_ID");
				int reiId = rs.getInt("REIMBURSEMENTS_ID");
				int manId = rs.getInt("REIMBURSEMENTS_MANAGERID");
				er.add(new Employee_Reimbursements(empId, reiId, manId));
			}
			
			// Get all requests
			for(int i = 0; i < er.size(); i++) {
				sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, er.get(i).getReiId());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int pId = rs.getInt("REIMBURSEMENTS_ID");
					String status = rs.getString("REIMBURSEMENTS_STATUS");
					String reqNotes = rs.getString("REIMBURSEMENTS_REQUESTNOTES");
					BigDecimal amount = new BigDecimal(String.valueOf(rs.getFloat("REIMBURSEMENTS_AMOUNT")));
					Date reqQate = rs.getDate("REIMBURSEMENTS_REQUESTDATE");
					String img = rs.getString("REIMBURSEMENTS_IMG");
					r.add(new Reimbursements(pId, status, reqNotes, amount, reqQate, img));
				}
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return r;
	}

}
