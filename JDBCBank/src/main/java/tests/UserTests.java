package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import main.User;

public class UserTests {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	User a = new User();
	
	@Test
	public void CheckForValidLogin () {
		assertEquals(true, a.checkPassword("kevin", "password"));
		assertEquals(true, a.checkPassword("tad", "password"));
		assertEquals(false, a.checkPassword("kev", "password"));
		assertEquals(false, a.checkPassword("5", "0"));
	}
	
	@Test
	public void CheckForUserExists() {
		assertEquals(true, a.checkUser("kevin"));
		assertEquals(true, a.checkUser("tad"));
		assertEquals(false, a.checkUser("k"));
	}
}
