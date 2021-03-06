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
import domain.Employee_Reimbursements_Reimb;
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
			// Updates Reimbursement with the correct reimbursement id and date
			r.setId(rId);
			r.setReqDate(d);

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
			for (int i = 0; i < er.size(); i++) {
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
			for (int i = 0; i < er.size(); i++) {
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

	@Override
	public Reimbursements updateReimbursementFileName(int id, String fileName) {
		Reimbursements r = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Reimbursement
			String sql = "UPDATE REIMBURSEMENTS SET REIMBURSEMENTS_IMG = ? WHERE REIMBURSEMENTS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fileName);
			pstmt.setInt(2, id);
			pstmt.executeQuery();

			// Get reimbursement
			sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_ID = ?";
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
	public List<Reimbursements> getResolvedReimbursements(int id) {
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

			// Search list for resolved requests
			for (int i = 0; i < er.size(); i++) {
				sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_ID = ? AND (REIMBURSEMENTS_STATUS = 'APPROVED' OR REIMBURSEMENTS_STATUS = 'DENIED')";
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
	public Reimbursements approveReimbursement(int id, int mId) {
		Reimbursements r = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Reimbursement
			String sql = "UPDATE REIMBURSEMENTS SET REIMBURSEMENTS_STATUS = 'APPROVED' WHERE REIMBURSEMENTS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeQuery();

			// Update Employee_Reimbursement table with the resolving manager
			sql = "UPDATE EMPLOYEE_REIMBURSEMENTS SET REIMBURSEMENTS_MANAGERID = ? WHERE REIMBURSEMENTS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mId);
			pstmt.setInt(2, id);
			pstmt.executeQuery();

			// Get reimbursement
			sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_ID = ?";
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
	public List<Employee_Reimbursements_Reimb> getAllResolvedReimbursements() {
		List<Reimbursements> r = new ArrayList<>();
		List<Employee_Reimbursements> er = new ArrayList<>();
		List<Employee_Reimbursements_Reimb> err = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Search for matching reimbursements with employee id
			String sql = "SELECT * FROM EMPLOYEE_REIMBURSEMENTS ORDER BY REIMBURSEMENTS_ID ASC";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int empId = rs.getInt("EMPLOYEE_ID");
				int reiId = rs.getInt("REIMBURSEMENTS_ID");
				int manId = rs.getInt("REIMBURSEMENTS_MANAGERID");
				er.add(new Employee_Reimbursements(empId, reiId, manId));
			}

			// Search list for resolved requests
			sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_STATUS = 'APPROVED' OR REIMBURSEMENTS_STATUS = 'DENIED' ORDER BY REIMBURSEMENTS_ID ASC";
			pstmt = con.prepareStatement(sql);
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

			for (int i = 0; i < r.size(); i++) {
				err.add(new Employee_Reimbursements_Reimb(er.get(i).getEmpId(), er.get(i).getReiId(),
						er.get(i).getManId(), r.get(i).getStatus(), r.get(i).getReqNotes(), r.get(i).getAmount(),
						r.get(i).getReqDate(), r.get(i).getImg()));
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return err;
	}

	@Override
	public Reimbursements denyReimbursement(int id, int mId) {
		Reimbursements r = null;
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Update Reimbursement
			String sql = "UPDATE REIMBURSEMENTS SET REIMBURSEMENTS_STATUS = 'DENIED' WHERE REIMBURSEMENTS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeQuery();

			// Update Employee_Reimbursement table with the resolving manager
			sql = "UPDATE EMPLOYEE_REIMBURSEMENTS SET REIMBURSEMENTS_MANAGERID = ? WHERE REIMBURSEMENTS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mId);
			pstmt.setInt(2, id);
			pstmt.executeQuery();

			// Get reimbursement
			sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_ID = ?";
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
	public List<Employee_Reimbursements_Reimb> getAllPendingReimbursements() {
		List<Reimbursements> r = new ArrayList<>();
		List<Employee_Reimbursements> er = new ArrayList<>();
		List<Employee_Reimbursements_Reimb> err = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Search for matching reimbursements with employee id
			String sql = "SELECT * FROM EMPLOYEE_REIMBURSEMENTS ORDER BY REIMBURSEMENTS_ID ASC";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int empId = rs.getInt("EMPLOYEE_ID");
				int reiId = rs.getInt("REIMBURSEMENTS_ID");
				int manId = rs.getInt("REIMBURSEMENTS_MANAGERID");
				er.add(new Employee_Reimbursements(empId, reiId, manId));
			}

			// Search list for pending requests
			sql = "SELECT * FROM REIMBURSEMENTS WHERE REIMBURSEMENTS_STATUS = 'PENDING' ORDER BY REIMBURSEMENTS_ID ASC";
			pstmt = con.prepareStatement(sql);
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

			for (int i = 0; i < r.size(); i++) {
				for (int j = 0; j < er.size(); j++) {
					if(r.get(i).getId() == er.get(j).getReiId()) {
						err.add(new Employee_Reimbursements_Reimb(er.get(j).getEmpId(), er.get(j).getReiId(),
								er.get(j).getManId(), r.get(i).getStatus(), r.get(i).getReqNotes(), r.get(i).getAmount(),
								r.get(i).getReqDate(), r.get(i).getImg()));
						break;
					}
				}
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return err;
	}

	@Override
	public List<Employee_Reimbursements_Reimb> getAllReimbursementsByEmpId(int id) {
		List<Reimbursements> r = new ArrayList<>();
		List<Employee_Reimbursements> er = new ArrayList<>();
		List<Employee_Reimbursements_Reimb> err = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {

			// Search for matching reimbursements with employee id
			String sql = "SELECT * FROM EMPLOYEE_REIMBURSEMENTS WHERE EMPLOYEE_ID = ? ORDER BY REIMBURSEMENTS_ID ASC";
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
			sql = "SELECT * FROM REIMBURSEMENTS ORDER BY REIMBURSEMENTS_ID ASC";
			pstmt = con.prepareStatement(sql);
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

			for (int i = 0; i < er.size(); i++) {
				for (int j = 0; j < r.size(); j++) {
					if(r.get(j).getId() == er.get(i).getReiId()) {
						err.add(new Employee_Reimbursements_Reimb(er.get(i).getEmpId(), er.get(i).getReiId(),
								er.get(i).getManId(), r.get(j).getStatus(), r.get(j).getReqNotes(), r.get(j).getAmount(),
								r.get(j).getReqDate(), r.get(j).getImg()));
						break;
					}
				}
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return err;
	}

}
