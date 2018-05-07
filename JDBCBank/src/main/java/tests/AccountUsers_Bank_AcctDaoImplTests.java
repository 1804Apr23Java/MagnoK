package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dao.AccountUsers_Bank_AcctDao;
import dao.AccountUsers_Bank_AcctDaoImpl;

public class AccountUsers_Bank_AcctDaoImplTests {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	AccountUsers_Bank_AcctDao a = new AccountUsers_Bank_AcctDaoImpl();
	
	@Test
	public void CheckIfBankAccountExists() {
		
		assertEquals(true, a.checkAccount(62));
		assertEquals(true, a.checkAccount(63));
		assertEquals(false, a.checkAccount(47));
		
	}

}
