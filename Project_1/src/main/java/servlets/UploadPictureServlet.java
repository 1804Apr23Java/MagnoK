package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dao.ReimbursementsDao;
import dao.ReimbursementsDaoImpl;
import domain.Employee;
import domain.Reimbursements;

/**
 * Servlet implementation class UploadReimbServlet
 */
public class UploadPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadPictureServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession(false);
		// // Checks to see if a valid session exists
		// if (session != null && session.getAttribute("username") != null) {
		// request.getRequestDispatcher("pages/EmpOptions/UploadPicture.html").forward(request,
		// response);
		// } else {
		// // If there is no session (no one logged in yet) will send user to login page
		// response.sendRedirect("login");
		// }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		// Get username from login servlet
		String username = (String) session.getAttribute("username");
		String reqNotes = (String) session.getAttribute("reqNotes");
		String amt = (String) session.getAttribute("amt");
		BigDecimal amount = new BigDecimal(amt);
		String fName = "No file has been uploaded";

		// Data Access Objects
		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		EmployeeDao ed = new EmployeeDaoImpl();

		Employee e = ed.getEmployeeByUsername(username);
		Reimbursements r = rd.createReimbursement(e, new Reimbursements(0, null, reqNotes, amount, null, fName));

		// Uploading Picture to server
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> files = null;
		try {
			files = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}

		// Check that we have a file upload request
		if (files.get(0).getSize() != 0) {
			try {
				// Only uploads if there is a file
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
			// Create update reimbursement in ReimbursementsDao
			r = rd.updateReimbursementFileName(r.getId(), fName);
		}
		
		
		// Set the reimbursement_id as a session variable
		session.setAttribute("reimbursement_id", r.getId());
		response.sendRedirect("confirmation");
		
	}

}
