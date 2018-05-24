package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dao.ReimbursementsDao;
import dao.ReimbursementsDaoImpl;
import domain.Employee;
import domain.Employee_Reimbursements_Reimb;

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
	
	@Test
	public void UpdatingEmailCorrectly() {	
		
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		String newEmail = "test@gmail.com";
		Employee kUpdated = null;

		// Correct User Info (Testing new email)
		kUpdated = e.updateEmployeeEmail(1, newEmail);
		assertEquals(newEmail, kUpdated.getEmail());
			
	}
	
	@Test
	public void UpdatingBirthdayCorrectly() {	
		
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		String newBirthdayStr = "1994-09-29";
		Date newBirthday = Date.valueOf(newBirthdayStr);	
		Employee kUpdated = null;

		// Correct User Info (Testing new email)
		kUpdated = e.updateEmployeeBirthday(1, newBirthday);
		assertEquals(newBirthday, kUpdated.getBirthday());
			
	}
	
	@Test
	public void UpdatingAddressCorrectly() {	
		
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		String newAddress = "2919 Network Place";
		Employee kUpdated = null;

		// Correct User Info (Testing new email)
		kUpdated = e.updateEmployeeAddress(1, newAddress);
		assertEquals(newAddress, kUpdated.getAddress());
			
	}
	
	@Test
	public void UpdatingCityCorrectly() {	
		
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		String newCity = "Lutz";
		Employee kUpdated = null;

		// Correct User Info (Testing new email)
		kUpdated = e.updateEmployeeCity(1, newCity);
		assertEquals(newCity, kUpdated.getCity());
			
	}
	
	@Test
	public void UpdatingStateCorrectly() {	
		
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		String newState = "FL";
		Employee kUpdated = null;

		// Correct User Info (Testing new email)
		kUpdated = e.updateEmployeeState(1, newState);
		assertEquals(newState, kUpdated.getState());
			
	}
	
	@Test
	public void UpdatingZipCorrectly() {	
		
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		String newZip = "33559";
		Employee kUpdated = null;

		// Correct User Info (Testing new email)
		kUpdated = e.updateEmployeeZip(1, newZip);
		assertEquals(newZip, kUpdated.getZip());
			
	}
	
	@Test
	public void UpdatingPhoneNumberCorrectly() {	
		
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		String newPhone = "+1(718)-687-9478";
		Employee kUpdated = null;

		// Correct User Info (Testing new email)
		kUpdated = e.updateEmployeePhoneNumber(1, newPhone);
		assertEquals(newPhone, kUpdated.getPhone());
			
	}
	
	@Test
	public void GettingAllEmployeesCorrectly() {

		List<Employee> emp = e.getAllEmployees();

		// Checking output
		for (int i = 0; i < emp.size(); i++) {

			System.out.println(i + " " + emp.get(i));
		}

		// There is at least 2 employees (test employee and manager)
		assertTrue(emp.size() > 0);
	}

}
