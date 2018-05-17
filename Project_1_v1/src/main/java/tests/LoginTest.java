package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import main.Login;

public class LoginTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	Login l = new Login();
	
	@Test
	public void CheckForValidLoginMain() {	
		
		// Correct User name and password
		assertEquals(true, l.checkPass("kmagno", "password"));
		// Correct User name wrong password
		assertEquals(false, l.checkPass("kmagno", "p4ssw0rd"));
		// Wrong username wrong password
		assertEquals(false, l.checkPass("ljames", "king"));
		
	}

}
