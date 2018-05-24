package dao;

import java.util.List;

import domain.Employee;
import domain.Employee_Reimbursements_Reimb;
import domain.Reimbursements;

public interface ReimbursementsDao {
	public Reimbursements createReimbursement(Employee e, Reimbursements r);
	public Reimbursements getReimbursementByID(int id);
	public List<Reimbursements> getPendingReimbursements(int id);
	public List<Reimbursements> getResolvedReimbursements(int id);
	public List<Reimbursements> getAllReimbursements(int id);
	public Reimbursements approveReimbursement(int id, int mId);
	public Reimbursements denyReimbursement(int id, int mId);
	public Reimbursements updateReimbursementFileName(int id, String fileName);
	public List<Employee_Reimbursements_Reimb> getAllResolvedReimbursements();
	public List<Employee_Reimbursements_Reimb> getAllPendingReimbursements();
	public List<Employee_Reimbursements_Reimb> getAllReimbursementsByEmpId(int id);
}
