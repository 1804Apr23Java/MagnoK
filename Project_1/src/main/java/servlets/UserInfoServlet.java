package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import domain.Employee;

public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInfoServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			response.setContentType("application/json");
			response.getWriter().write("{\"username\":\""+session.getAttribute("username")+"\"}");
		} else {
			response.setContentType("application/json");
			response.getWriter().write("{\"username\":null}");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get current session
//		HttpSession session = request.getSession(false);
//		response.setContentType("application/json");
//		PrintWriter pw = response.getWriter();
//
//		// Get username from login servlet
//		String username = (String) session.getAttribute("username");
//		// Get employee information
//		EmployeeDao e = new EmployeeDaoImpl();
//		Employee emp = e.getEmployeeByUsername(username);
//		
//		System.out.println(emp);
//
//		response.setContentType("application/json");
//		ObjectMapper om = new ObjectMapper();
//		String userString = om.writeValueAsString(emp);
//		//response.getWriter().write("{\"username\":" + userString + "}");
//		response.getWriter().write("{\"username\":\""+emp.getUsername()+"\"}");
	}

}
