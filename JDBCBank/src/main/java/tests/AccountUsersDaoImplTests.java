package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dao.AccountUsersDao;
import dao.AccountUsersDaoImpl;
import domain.AccountUsers;

public class AccountUsersDaoImplTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	AccountUsersDao au = new AccountUsersDaoImpl();
	
	@Test
	public void CheckForValidLogin() {	
		
		assertEquals(true, au.checkPassword("kevin", "password"));
		assertEquals(true, au.checkPassword("tad", "password"));
		assertEquals(false, au.checkPassword("tad", "p4assw0rd"));
		
	}
	
	@Test
	public void CheckForCorrectAccountReturn () {
		
		AccountUsers kevin = new AccountUsers(62, "kevin", "password", true);
		AccountUsers tad = new AccountUsers(63, "tad", "password", true);
		
		assertEquals(kevin.getId(), au.getAUByUsername("kevin").getId());
		assertEquals(kevin.getUsername(), au.getAUByUsername("kevin").getUsername());
		assertEquals(kevin.getPassword(), au.getAUByUsername("kevin").getPassword());
		
		assertEquals(tad.getId(), au.getAUByUsername("tad").getId());
		assertEquals(tad.getUsername(), au.getAUByUsername("tad").getUsername());
		assertEquals(tad.getPassword(), au.getAUByUsername("tad").getPassword());
		
	}
	
	@Test
	public void CheckForUserExists() {
		
		assertEquals(true, au.userExists("kevin"));
		assertEquals(true, au.userExists("tad"));
		assertEquals(false, au.userExists("shnitzl"));
		
	}
	
}
