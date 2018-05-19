package domain;

public class Employee_Reimbursements {
	public Employee_Reimbursements() {
		super();
	}
	public Employee_Reimbursements(int empId, int reiId, int manId) {
		super();
		this.empId = empId;
		this.reiId = reiId;
		this.manId = manId;
	}
	int empId;
	int reiId;
	int manId;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getReiId() {
		return reiId;
	}
	public void setReiId(int reiId) {
		this.reiId = reiId;
	}
	public int getManId() {
		return manId;
	}
	public void setManId(int manId) {
		this.manId = manId;
	}
	@Override
	public String toString() {
		return "Employee_Reimbursements [empId=" + empId + ", reiId=" + reiId + ", manId=" + manId + "]";
	}
}
