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
@Table(name = "CURRENTHANDS")
public class CurrentHands implements Serializable{

	public CurrentHands() {
		super();
	}

	public CurrentHands(Users user, String hand, int winnings, boolean hasFolded, int playerOrder) {
		super();
		this.user = user;
		this.hand = hand;
		this.winnings = winnings;
		this.hasFolded = hasFolded;
		this.playerOrder = playerOrder;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1235010807146080359L;
	
	@Id
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private Users user;
	
	@Column(name = "HAND")
	private String hand;
	
	@Column(name = "WINNINGS")
	private int winnings;
	
	@Column(name = "HAS_FOLDED")
	private boolean hasFolded;
	
	@Column(name = "PLAYER_ORDER")
	private int playerOrder;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getHand() {
		return hand;
	}

	public void setHand(String hand) {
		this.hand = hand;
	}

	public int getWinnings() {
		return winnings;
	}

	public void setWinnings(int winnings) {
		this.winnings = winnings;
	}

	public boolean isHasFolded() {
		return hasFolded;
	}

	public void setHasFolded(boolean hasFolded) {
		this.hasFolded = hasFolded;
	}

	public int getPlayerOrder() {
		return playerOrder;
	}

	public void setPlayerOrder(int playerOrder) {
		this.playerOrder = playerOrder;
	}

	@Override
	public String toString() {
		return "CurrentHands [user=" + user + ", hand=" + hand + ", winnings=" + winnings + ", hasFolded=" + hasFolded
				+ ", playerOrder=" + playerOrder + "]";
	}
}
