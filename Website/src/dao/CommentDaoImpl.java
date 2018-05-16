package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Comments;
import util.ConnectionUtil;

public class CommentDaoImpl implements CommentDao {
	
	private String filename = "connection.properties";

	@Override
	public List<Comments> getComments() {
		List<Comments> com = new ArrayList<>();
		PreparedStatement pstmt = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "SELECT * FROM COMMENTS";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			// do something with result
			while (rs.next()) {
				int pId = rs.getInt("COMMENTID");
				String email = rs.getString("EMAIL");
				String comment = rs.getString("COMMENTPOST");
				com.add(new Comments(pId, email, comment));
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return com;
	}

	@Override
	public void postComment(String email, String commentPost) {
		PreparedStatement pstmt = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {

			// use a prepared statement
			String sql = "INSERT INTO COMMENTS(EMAIL, COMMENTPOST) VALUES(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,email);
			pstmt.setString(2, commentPost);
			pstmt.executeQuery();

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
