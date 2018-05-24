package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import domain.Employee;

public class EmpUpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmpUpdateInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		EmployeeDao ed = new EmployeeDaoImpl();
		int id = (int) session.getAttribute("empId");
		String username = (String) session.getAttribute("username");
		Employee e = ed.getEmployeeByUsername(username);
		
		String email = request.getParameter("email");	
		if(!email.equals("")) {
			e = ed.updateEmployeeEmail(id, email);
			System.out.println("Updated Email!");
		}
		
		String birthdayStr = request.getParameter("bday");
		Date birthday = null;
		if(!birthdayStr.equals(""))  {
			birthday =  Date.valueOf(birthdayStr);	
			e = ed.updateEmployeeBirthday(id, birthday);
			System.out.println("Updated Birthday!");
		}		
		
		String address = request.getParameter("address");
		if(!address.equals("")) {
			e = ed.updateEmployeeAddress(id, address);
			System.out.println("Updated Address!");
		}
		
		String city = request.getParameter("city");
		if(!city.equals("")) {
			e = ed.updateEmployeeCity(id, city);
			System.out.println("Updated City!");
		}
		
		String state = request.getParameter("state");
		if(!state.equals("")) {
			e = ed.updateEmployeeState(id, state);
			System.out.println("Updated State!");
		}
		
		String zip = request.getParameter("zip");
		if(!zip.equals("")) {
			e = ed.updateEmployeeZip(id, zip);
			System.out.println("Updated Zip!");
		}
		
		String phone = request.getParameter("phone");
		if(!phone.equals("")) {
			e = ed.updateEmployeePhoneNumber(id, phone);
			System.out.println("Updated Phone!");
		}
		
		request.getRequestDispatcher("empviewinfo").forward(request, response);
	}

}
