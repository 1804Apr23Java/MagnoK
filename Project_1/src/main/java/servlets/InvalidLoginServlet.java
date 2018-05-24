package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.RequestHelper;

public class InvalidLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public InvalidLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Master Servlet starts at the main login page
		RequestDispatcher rd = request.getRequestDispatcher("pages/InvalidLogin.html");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Gets username and password from form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//Pass to request helper to check user authentication and to determine whether to direct to Manager or Employee page
		String destination = RequestHelper.userLogin(username, password);
		
		//If the user successfully logs in
		HttpSession session = request.getSession();
		if(destination.equals("Employee") || destination.equals("Manager")) {
			session.setAttribute("username", username);
		}
		
		response.sendRedirect(destination);
	}

}
