package dao;

import java.util.List;

import domain.Employee;
import domain.Reimbursements;

public interface ReimbursementsDao {
	public Reimbursements createReimbursement(Employee e, Reimbursements r);
	public Reimbursements getReimbursementByID(int id);
	public List<Reimbursements> getPendingReimbursements(int id);
	public List<Reimbursements> getResolvedReimbursements(int id);
	public List<Reimbursements> getAllReimbursements(int id);
	public Reimbursements approveReimbursement(int id);
	//Test
	public Reimbursements updateReimbursementFileName(int id, String fileName);
}
