package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dao.ReimbursementsDao;
import dao.ReimbursementsDaoImpl;
import domain.Employee;
import domain.Reimbursements;

/**
 * Servlet implementation class ReimbursementInfoServlet
 */
public class ReimbursementInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("application/json");
		
		if (session != null) {
			// Get Reimbursement ID
			int rId = (int) session.getAttribute("reimbursement_id");
			
			// Data Access Objects
			ReimbursementsDao rd = new ReimbursementsDaoImpl();
			Reimbursements r = rd.getReimbursementByID(rId);
			
			response.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();
			String reimbInfo = om.writeValueAsString(r);
			
			response.getWriter().write(reimbInfo);
		} else {
			// If there is no session up make the username null (Will send back to login screen)
			response.setContentType("application/json");
			response.getWriter().write("{\"username\":null}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
