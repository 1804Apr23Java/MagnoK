package domain;

public class Bank_Acct_Transactions {
	public Bank_Acct_Transactions() {
		super();
	}
	public Bank_Acct_Transactions(int baId, int trId) {
		super();
		this.baId = baId;
		this.trId = trId;
	}
	private int baId;
	private int trId;
	public int getBaId() {
		return baId;
	}
	public void setBaId(int baId) {
		this.baId = baId;
	}
	public int getTrId() {
		return trId;
	}
	public void setTrId(int trId) {
		this.trId = trId;
	}
	@Override
	public String toString() {
		return "Bank_Acct_Transactions [baId=" + baId + ", trId=" + trId + "]";
	}
}
