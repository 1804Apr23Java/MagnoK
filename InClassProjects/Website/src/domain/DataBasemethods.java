package domain;
import dao.CommentDao;
import dao.CommentDaoImpl;

public class DataBasemethods {
	public void addToDb(String email, String comment) {
		CommentDao c = new CommentDaoImpl();
		c.postComment(email, comment);
	}
}
