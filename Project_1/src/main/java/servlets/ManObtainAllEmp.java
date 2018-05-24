package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import domain.Employee;

public class ManObtainAllEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManObtainAllEmp() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {

			// Data Access Objects
			EmployeeDao ed = new EmployeeDaoImpl();

			// List of Resolved Reimbursements
			List<Employee> e = ed.getAllEmployees();

			response.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();
			String userInfo = om.writeValueAsString(e);

			response.getWriter().write("{\"users\":" + userInfo + "}");
		} else {
			// If there is no session up make the username null (Will send back to login
			// screen)
			response.setContentType("application/json");
			response.getWriter().write("{\"username\":null}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
