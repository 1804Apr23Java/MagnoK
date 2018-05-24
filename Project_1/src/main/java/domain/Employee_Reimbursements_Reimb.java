package domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Employee_Reimbursements_Reimb {
	public Employee_Reimbursements_Reimb() {
		super();
	}
	public Employee_Reimbursements_Reimb(int empId, int reiId, int manId, String status, String reqNotes,
			BigDecimal amount, Date reqDate, String img) {
		super();
		this.empId = empId;
		this.reiId = reiId;
		this.manId = manId;
		this.status = status;
		this.reqNotes = reqNotes;
		this.amount = amount;
		this.reqDate = reqDate;
		this.img = img;
	}
	int empId;
	int reiId;
	int manId;
	private String status;
	private String reqNotes;
	private BigDecimal amount;
	private Date reqDate;
	private String img;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReqNotes() {
		return reqNotes;
	}
	public void setReqNotes(String reqNotes) {
		this.reqNotes = reqNotes;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Employee_Reimbursements_Reimb [empId=" + empId + ", reiId=" + reiId + ", manId=" + manId + ", status="
				+ status + ", reqNotes=" + reqNotes + ", amount=" + amount + ", reqDate=" + reqDate + ", img=" + img
				+ "]";
	}
}
