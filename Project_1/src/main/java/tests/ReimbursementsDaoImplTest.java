package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dao.ReimbursementsDao;
import dao.ReimbursementsDaoImpl;
import domain.Employee;
import domain.Reimbursements;

public class ReimbursementsDaoImplTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	ReimbursementsDao rd = new ReimbursementsDaoImpl();

	@Test
	public void SuccessfulReimbursementRequest() {

		// Get current date
		Calendar c = Calendar.getInstance();
		Date d = new Date(c.getTime().getTime());

		// Check reimbursement submission
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null,
				null, false);
		BigDecimal amount = new BigDecimal("44.44");
		
		// Reimbursement that was just created (ID Will be changed as it is created
		Reimbursements reimbReqDB = rd.createReimbursement(k, new Reimbursements(0, null, "Dummy request for testing", amount, d, null));
		// Get Reimbursement by ID next and compare if they match
		// Reimbursement Request from database
		assertEquals(reimbReqDB.getReqNotes(), rd.getReimbursementByID(reimbReqDB.getId()).getReqNotes());
	
	}
	
	@Test
	public void GettingPendingRequestsCorrectly() {
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		Employee kManager = new Employee(2, "Kevin", "Magno", "kmagnomanager", "p4ssw0rd", null, null, null, null, null, null, null, null, true);
		
		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		List<Reimbursements> rk = rd.getPendingReimbursements(k.getId());
		List<Reimbursements> rkman = rd.getPendingReimbursements(kManager.getId());
		List<Reimbursements> rnon = rd.getPendingReimbursements(-1);
		
		// Test employee should have at least one reimbursement
		assertTrue(rk.size() > 0);
		// Manager should not be able to make any reimbursements (list size should be 0)
		assertEquals(0, rkman.size());
		// Non-existent employee should not have any reimbursements
		assertEquals(0, rnon.size());

	}
	
	@Test
	public void GettingAllRequestsCorrectly() {
		Employee k = new Employee(1, "kevin", "magno", "kmagno", "password", null, null, null, null, null, null, null, null, false);
		Employee kManager = new Employee(2, "Kevin", "Magno", "kmagnomanager", "p4ssw0rd", null, null, null, null, null, null, null, null, true);
		
		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		List<Reimbursements> rk = rd.getAllReimbursements(k.getId());
		List<Reimbursements> rkman = rd.getAllReimbursements(kManager.getId());
		List<Reimbursements> rnon = rd.getAllReimbursements(-1);
		
		// Test employee should have at least one reimbursement
		assertTrue(rk.size() > 0);
		// Manager should not be able to make any reimbursements (list size should be 0)
		assertEquals(0, rkman.size());
		// Non-existent employee should not have any reimbursements
		assertEquals(0, rnon.size());

	}

}
