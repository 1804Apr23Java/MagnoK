package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STATS")
public class Stats implements Serializable{

	public Stats() {
		super();
	}

	public Stats(Users user, int totalWinnings, int wins, int losses) {
		super();
		this.user = user;
		this.totalWinnings = totalWinnings;
		this.wins = wins;
		this.losses = losses;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3852282483959221088L;
	
	@Id
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private Users user;
	
	@Column(name = "TOTAL_WINNINGS")
	private int totalWinnings;
	
	@Column(name = "WINS")
	private int wins;
	
	@Column(name = "LOSSES")
	private int losses;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getTotalWinnings() {
		return totalWinnings;
	}

	public void setTotalWinnings(int totalWinnings) {
		this.totalWinnings = totalWinnings;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	@Override
	public String toString() {
		return "Stats [user=" + user + ", totalWinnings=" + totalWinnings + ", wins=" + wins + ", losses=" + losses
				+ "]";
	}
	
}
