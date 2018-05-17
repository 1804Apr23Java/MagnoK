package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import domain.Employee;

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
	
	@Test
	public void ObtainingCorrectUserInfo() {	
		
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		Employee kManager = new Employee(2, "Kevin", "Magno", "kmagnomanager", "p4ssw0rd", null, null, null, null, null, null, null, null, true);
		
		// Correct User Info (Testing unique values)
		assertEquals(k.getId(), e.getEmployeeByUsername("kmagno").getId());
		assertEquals(k.getUsername(), e.getEmployeeByUsername("kmagno").getUsername());
		assertEquals(k.isManager(), e.getEmployeeByUsername("kmagno").isManager());
		assertEquals(kManager.getId(), e.getEmployeeByUsername("kmagnomanager").getId());
		assertEquals(kManager.getUsername(), e.getEmployeeByUsername("kmagnomanager").getUsername());
		assertEquals(kManager.isManager(), e.getEmployeeByUsername("kmagnomanager").isManager());
		// Empty User (Doesn't Exist)
		assertEquals(null, e.getEmployeeByUsername("kmag"));	
	}
	
	@Test
	public void CheckingManagerStatus() {	
		
		// Testing manager status of existing users
		assertEquals(false, e.getManagerStatus("kmagno"));
		assertEquals(true, e.getManagerStatus("kmagnomanager"));
		// Empty User (Doesn't Exist)
		assertEquals(null, e.getEmployeeByUsername("kmag"));	
	}

}
