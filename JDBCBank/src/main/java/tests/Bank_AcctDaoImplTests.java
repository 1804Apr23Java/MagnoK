package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dao.Bank_AcctDao;
import dao.Bank_AcctDaoImpl;
import domain.Bank_Acct;

public class Bank_AcctDaoImplTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	Bank_AcctDao b = new Bank_AcctDaoImpl();
	
	@Test
	public void CheckForCorrectAccountReturn () {
		
		Bank_Acct kevin = new Bank_Acct(24, 90080);
		Bank_Acct tad = new Bank_Acct(23, 50000);
		
		assertEquals(kevin.getBalance(), b.getByBAId(24).getBalance());

		assertEquals(tad.getBalance(), b.getByBAId(23).getBalance());
		
	}
	
	@Test
	public void CheckifAccountAndBankAccountsMatch() {
		
		Bank_Acct kevin = new Bank_Acct(24, 90080);
		Bank_Acct tad = new Bank_Acct(23, 50000);
		
		// From Kevin's account number and bank account number
		assertEquals(true, b.checkAccountNum(24, 62));
		
		// From Tad's account number and bank account number
		assertEquals(true, b.checkAccountNum(23, 63));
		
		// Comparing Kevin's account number and Tad's bank account number
		assertEquals(false, b.checkAccountNum(24, 63));
	}
}
