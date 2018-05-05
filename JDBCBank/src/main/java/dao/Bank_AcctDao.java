package dao;

import domain.Bank_Acct;

public interface Bank_AcctDao {
	public Bank_Acct getByBAId(int id);
	public void createBankAcct(int startingBal, int id);
	public boolean checkAccountNum(int id);
	public void withdraw(int id,int amount, int modifier);
}
