package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADMINS")
public class Admins implements Serializable{

	public Admins() {
		super();
	}

	public Admins(Users user) {
		super();
		this.user = user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4331543760144852691L;
	
	@Id
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Admins [user=" + user + "]";
	}
	
}
