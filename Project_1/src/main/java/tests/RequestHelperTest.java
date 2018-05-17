package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import main.Login;
import util.RequestHelper;

public class RequestHelperTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void CorrectRedirect() {	
		
		// Checking for a Manager
		assertEquals("Manager", RequestHelper.userLogin("kmagnomanager", "p4ssw0rd"));
		// Checking for a Employee
		assertEquals("Employee", RequestHelper.userLogin("kmagno", "password"));
		// Checking for non-existent Employee or Manager
		assertEquals("InvalidLogin", RequestHelper.userLogin("MarkZuckerberg", "facebookrox"));
		
	}
	
	@Test
	public void Authentication() {	
		
		// Correct User name and password
		assertEquals(true, RequestHelper.authenticateUser("kmagno", "password"));
		// Correct User name wrong password
		assertEquals(false, RequestHelper.authenticateUser("kmagno", "p4ssw0rd"));
		// Wrong username wrong password
		assertEquals(false, RequestHelper.authenticateUser("ljames", "king"));
		
	}
	
	@Test
	public void ManagerStatus() {	
		
		// Checking for a Manager
		assertEquals(true, RequestHelper.isManager("kmagnomanager"));
		// Checking for a Employee
		assertEquals(false, RequestHelper.isManager("kmagno"));
		// Checking for non-existent Employee or Manager
		assertEquals(false, RequestHelper.isManager("elonMusk"));
		
	}
	
}
