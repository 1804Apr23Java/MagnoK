package servlets;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dao.ReimbursementsDao;
import dao.ReimbursementsDaoImpl;
import domain.Employee;
import domain.Reimbursements;

/**
 * Servlet implementation class SubmitReimbServlet
 */
public class SubmitReimbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubmitReimbServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// Checks to see if a valid session exists
		if (session != null && session.getAttribute("username") != null) {
			request.getRequestDispatcher("pages/EmpOptions/SubmitReimb.html").forward(request, response);
		} else {
			// If there is no session (no one logged in yet) will send user to login page
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// Get username from login servlet
		String username = (String) session.getAttribute("username");

		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		EmployeeDao ed = new EmployeeDaoImpl();

		Employee e = ed.getEmployeeByUsername(username);

		// Get values for Reimbursement Submission
		String reqNotes = request.getParameter("requestNotes");
		String amt = request.getParameter("amount");

		System.out.println(reqNotes);
		System.out.println(amt);

		BigDecimal amount = new BigDecimal("0");
		String fName = "No file has been uploaded";

		Reimbursements r = rd.createReimbursement(e, new Reimbursements(0, null, reqNotes, amount, null, fName));

		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> files = null;
		try {
			files = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}

		// Only uploads if there is a file
		if (files.get(0).getSize() > 10) {
			try {
				for (FileItem item : files) {
					String ext = FilenameUtils.getExtension(item.getName());
					item.write(new File("C:\\GitRepos\\MagnoK\\Project_1\\src\\main\\webapp\\images\\"
							+ String.valueOf(r.getId() + "." + ext)));
					fName = String.valueOf(r.getId()) + "." + ext;
					System.out.println(item.getSize());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			r.setImg(fName);
			System.out.println("File Uploaded");
		}

		response.setContentType("application/json");
		ObjectMapper om = new ObjectMapper();
		String reimbursementInfo = om.writeValueAsString(r);
		response.getWriter().write(reimbursementInfo);
	}

}
