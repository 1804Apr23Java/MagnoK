package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import main.Bank;

public class BankTests {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	Bank b = new Bank();
	
	@Test
	public void AccountExistsCheck () {
		assertEquals(true, b.checkIfAccountExists(62));
		assertEquals(false, b.checkIfAccountExists(1));
		assertEquals(true, b.checkIfAccountExists(63));
	}
	
	@Test
	public void CheckifAccountAndBankAccountsMatch () {
		assertEquals(true, b.checkAccountNumber(24, 62));
		assertEquals(false, b.checkAccountNumber(24, 1));
		assertEquals(true, b.checkAccountNumber(23, 63));
	}

}
