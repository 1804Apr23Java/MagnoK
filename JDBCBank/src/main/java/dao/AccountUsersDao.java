package dao;

import java.util.List;

import domain.AccountUsers;

public interface AccountUsersDao {
	public AccountUsers getAUByUsername(String user);
	public void newUser(String user, String pass);
	public boolean userExists(String user);
	public boolean checkPassword(String user, String password);
	public void superUPrintOutUsers();
	public void superUGrantAccess(String user);
	public void superUDelete(String user);
}
