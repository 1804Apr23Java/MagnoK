import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CommentDao;
import dao.CommentDaoImpl;
import domain.Comments;
import util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {

		 try {
		 Connection con = ConnectionUtil.getConnectionFromFile("connection.properties");
		 System.out.println(con.toString());
		 } catch (SQLException e) {
		 e.printStackTrace();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }

		CommentDao c = new CommentDaoImpl();

		// Print out comments
//		List<Comments> cl = c.getComments();
//		System.out.println(cl.get(0).toString());
//		for(int i = 0; i < cl.size(); i++) {
//			System.out.println(cl.get(i).toString());
//		}
		
//		c.postComment("a@gmail.com", "Las Vegas!!!");

	}

}
