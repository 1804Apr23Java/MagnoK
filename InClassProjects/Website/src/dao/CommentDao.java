package dao;

import java.util.List;

import domain.Comments;

public interface CommentDao {
	public List<Comments> getComments();
	public void postComment(String email, String commentPost);
}
