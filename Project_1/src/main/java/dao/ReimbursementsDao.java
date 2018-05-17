package dao;

import domain.Employee;
import domain.Reimbursements;

public interface ReimbursementsDao {
	public Reimbursements createReimbursement(Employee e, Reimbursements r);
}
