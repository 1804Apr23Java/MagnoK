package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

		String email = request.getParameter("email");	
		String birthdayStr = request.getParameter("bday");
		Date birthday = null;
		if(!birthdayStr.equals("")) 
			birthday =  Date.valueOf(birthdayStr);			
		
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String phone = request.getParameter("phone");
		
		if(email.equals("")) {
			System.out.println("Email is an empty string");
		}
		
		System.out.println(email);
		System.out.println(birthday);
		System.out.println(address);
		System.out.println(city);
		System.out.println(state);
		System.out.println(zip);
		System.out.println(phone);
	}

}
