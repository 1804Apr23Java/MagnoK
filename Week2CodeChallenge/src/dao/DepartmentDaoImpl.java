package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Department;
import util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	private String filename = "connection.properties";
	@Override
	public void printEachDeptWithAvgSal() {
		PreparedStatement pstmt = null;
		List<Department> d = new ArrayList<>();
		//int avgSal = 0;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Declare prepared statement
			String sql = "SELECT * FROM DEPARTMENT";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			// Give us each department info
			while (rs.next()) {
				int id = rs.getInt("DEPARTMENT_ID");
				String name = rs.getString("DEPARTMENT_NAME");
				d.add(new Department(id, name));
			}
			
			//Give us average salary of each department and prints
			int [] depts = new int[d.size()];
			for(int i = 0; i < d.size(); i++) {
				sql = "SELECT AVG(SALARY) FROM EMPLOYEE WHERE DEPARTMENT_ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, d.get(i).getId());
				rs = pstmt.executeQuery();
				
				// Give us each department info
				while (rs.next()) {
					int avg = rs.getInt("AVG(SALARY)");
					depts[i] = avg;
				}
				
				// Print out dept info and avg salary
				System.out.println(d.get(i).toString() + "Average Salary: " + depts[i]);
			}

			//for (int i = 0; i < d.size(); i++)
				//System.out.println(d.get(i));

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void giveRaiseToDept(int id) {
		CallableStatement cs = null;
		int avg_sal = 0;
		int val_dept = 0;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
			// Give raise to department specified by user
			String sql = "{call SP_GIVE_RAISE(?,10)}";
			cs = con.prepareCall(sql);
			cs.setInt(1,id);
			cs.execute();
			
			//Obtain new salaries
			printEachDeptWithAvgSal();

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
