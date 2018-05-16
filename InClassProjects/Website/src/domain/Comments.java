package domain;

public class Comments {
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comments(int id, String email, String post) {
		super();
		this.id = id;
		this.email = email;
		this.post = post;
	}
	private int id;
	private String email;
	private String post;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	@Override
	public String toString() {
		return "Commments [id=" + id + ", email=" + email + ", post=" + post + "]";
	}
}
