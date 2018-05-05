package domain;

public class Bank_Acct {
	
	public Bank_Acct() {
		super();
	}
	
	public Bank_Acct(int id, int balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	
	private int id;
	private int balance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Bank_Acct [id=" + id + ", balance=" + balance + "]";
	}
	
	
}
