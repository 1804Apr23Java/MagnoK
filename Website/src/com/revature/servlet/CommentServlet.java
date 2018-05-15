package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentServlet extends HttpServlet {

	private static final long serialVersionUID = -4205809626492202061L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
	
		RequestDispatcher rd = req.getRequestDispatcher("HomePage.html");
		rd.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String commentText = req.getParameter("comment");
		PrintWriter pw = resp.getWriter();
		String comment = "<html>";
		comment +="<p>Comment posted.</p>";
		comment +="Email: " + email + "</p>";
		comment +="Comment: " + commentText + "</p>";
		comment +="</html>";
		pw.write(comment);
		
//		pw.append("<body> COMMENT POSTED");
//		pw.append("<p style = \"margin:30px\"> Email: " + s1 + "</p>");
//		pw.append("<p style = \"margin:30px\"> Comment: " + s2 + "</p>");
//		pw.append("</body>");
	}
}
