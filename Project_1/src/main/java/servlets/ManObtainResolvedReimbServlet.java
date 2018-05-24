package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ReimbursementsDao;
import dao.ReimbursementsDaoImpl;
import domain.Employee_Reimbursements_Reimb;

/**
 * Servlet implementation class ManObtainResolvedReimbServlet
 */
public class ManObtainResolvedReimbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManObtainResolvedReimbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {

			// Data Access Objects
			ReimbursementsDao rd = new ReimbursementsDaoImpl();

			// List of Resolved Reimbursements
			List<Employee_Reimbursements_Reimb> r = rd.getAllResolvedReimbursements();

			response.setContentType("application/json");
			ObjectMapper om = new ObjectMapper();
			String reimbInfo = om.writeValueAsString(r);

			response.getWriter().write("{\"reimbursements\":"+reimbInfo+"}");
		} else {
			// If there is no session up make the username null (Will send back to login
			// screen)
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
