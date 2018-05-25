package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReimbursementsDao;
import dao.ReimbursementsDaoImpl;
import domain.Reimbursements;

/**
 * Servlet implementation class ManApproveServlet
 */
public class ManApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ManApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		String rIdStr = request.getParameter("rId");
		int rId = Integer.parseInt(rIdStr);
		int mId = (int) session.getAttribute("empId");
		
		Reimbursements r = rd.approveReimbursement(rId, mId);
		//System.out.println("Reimbursement: " + rId + " Approved by Manager: " + mId);
		
//		request.getRequestDispatcher("approvedeny").forward(request, response);
		response.sendRedirect("manviewresolvedreimb");
	}

}
