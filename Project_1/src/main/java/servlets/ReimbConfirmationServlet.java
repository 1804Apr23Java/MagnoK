package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ReimbursementsDao;
import dao.ReimbursementsDaoImpl;
import domain.Reimbursements;

/**
 * Servlet implementation class ReimbConfirmationServlet
 */
public class ReimbConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReimbConfirmationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		// Checks to see if a valid session exists
		if (session != null && session.getAttribute("username") != null) {
			
			request.getRequestDispatcher("pages/EmpOptions/ReimbConfirmation.html").forward(request, response);
			
		} else {
			// If there is no session (no one logged in yet) will send user to login page
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
