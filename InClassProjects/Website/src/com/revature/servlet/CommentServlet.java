package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.CommentDaoImpl;
import util.ConnectionUtil;

public class CommentServlet extends HttpServlet {

	private static final long serialVersionUID = -4205809626492202061L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("HomePage.html");
		rd.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String commentText = req.getParameter("comment");
		PrintWriter pw = resp.getWriter();

		CommentDao c = new CommentDaoImpl();

		// Add email and commentText to DB
		c.postComment(email, commentText);

		try {
			Connection con = ConnectionUtil.getConnectionFromFile("connection.properties");
			System.out.println(con.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Print out comments
		// List<Comments> cl = c.getComments();
		// String comment = "<html>";
		// comment += "<p>" + "WORKING" + "</p>";
		// for(int i = 0; i < cl.size(); i++) {
		// comment += "<p>" + cl.get(i).toString() + "</p>";
		// }
		// comment +="</html>";
		// pw.write(comment);

		resp.setContentType("text/html");
		String comment = "<html>";
		comment += "<p>Comment posted.</p>";
		comment += "Email: " + email + "</p>";
		comment += "Comment: " + commentText + "</p>";
		comment += "</html>";
		pw.write(comment);

		// pw.append("<body> COMMENT POSTED");
		// pw.append("<p style = \"margin:30px\"> Email: " + s1 + "</p>");
		// pw.append("<p style = \"margin:30px\"> Comment: " + s2 + "</p>");
		// pw.append("</body>");
	}
}
