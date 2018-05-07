package dao;

import domain.Transactions;

public interface TransactionsDao {
	public Transactions getTXById(int id);
	public void printTransactions(int id);
	public int calcNetIncome(int id);
}
