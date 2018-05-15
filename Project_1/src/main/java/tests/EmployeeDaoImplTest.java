package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

public class EmployeeDaoImplTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	EmployeeDao e = new EmployeeDaoImpl();
	
	@Test
	public void CheckForValidLogin() {	
		
		// Correct User name and password
		assertEquals(true, e.checkPassword("kmagno", "password"));
		// Correct User name wrong password
		assertEquals(false, e.checkPassword("kmagno", "p4ssw0rd"));
		// Wrong username wrong password
		assertEquals(false, e.checkPassword("ljames", "king"));
		
	}

}
