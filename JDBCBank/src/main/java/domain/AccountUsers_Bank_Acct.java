package domain;

public class AccountUsers_Bank_Acct {
	
	public AccountUsers_Bank_Acct() {
		super();
	}
	public AccountUsers_Bank_Acct(int auId, int baId) {
		super();
		this.auId = auId;
		this.baId = baId;
	}
	private int auId;
	private int baId;
	public int getAuId() {
		return auId;
	}
	public void setAuId(int auId) {
		this.auId = auId;
	}
	public int getBaId() {
		return baId;
	}
	public void setBaId(int baId) {
		this.baId = baId;
	}
	@Override
	public String toString() {
		//return "AccountUsers_Bank_Acct [auId=" + auId + ", baId=" + baId + "]";
		return "Bank Account Number: " +baId;
	}
	
}
