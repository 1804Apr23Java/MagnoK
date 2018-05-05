package domain;

public class AccountUsers {
	
	public AccountUsers() {
		super();
	}
	public AccountUsers(int id, String username, String password, boolean superuser) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.superuser = superuser;
	}
	
	private int id;
	private String username;
	private String password;
	private boolean superuser;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isSuperuser() {
		return superuser;
	}
	public void setSuperuser(boolean superuser) {
		this.superuser = superuser;
	}
	@Override
	public String toString() {
		return "AccountUsers [id=" + id + ", username=" + username + ", password=" + password + ", superuser="
				+ superuser + "]";
	}
}
