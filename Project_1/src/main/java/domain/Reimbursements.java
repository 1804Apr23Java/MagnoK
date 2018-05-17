package domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Reimbursements {
	public Reimbursements() {
		super();
	}
	public Reimbursements(int id, String status, String reqNotes, BigDecimal amount, Date reqDate, String img) {
		super();
		this.id = id;
		this.status = status;
		this.reqNotes = reqNotes;
		this.amount = amount;
		this.reqDate = reqDate;
		this.img = img;
	}
	private int id;
	private String status;
	private String reqNotes;
	private BigDecimal amount;
	private Date reqDate;
	private String img;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Reimbursements [id=" + id + ", status=" + status + ", reqNotes=" + reqNotes + ", amount=" + amount
				+ ", reqDate=" + reqDate + ", img=" + img + "]";
	}
}
